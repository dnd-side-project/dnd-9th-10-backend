#!/bin/bash

# 8081 포트 확인
ss -tuln | grep ":8081" > /dev/null
if [ $? -eq 0 ]; then
  CURRENT_PORT=8081
  TARGET_PORT=8082
fi

# 8082 포트 확인
ss -tuln | grep ":8082" > /dev/null
if [ $? -eq 0 ]; then
  CURRENT_PORT=8082
  TARGET_PORT=8081
fi


echo "TARGET_PORT is set to: $TARGET_PORT"


# 미사용 포트에 새로운 서버 실행
echo "Start updated server in ${TARGET_PORT}.."
sudo systemctl start bbok-${TARGET_PORT}
echo "Done."

# ALB의 타겟그룹 변경
echo "Change Target Group to bbok-target-group-${TARGET_PORT}"
if [ ${TARGET_PORT} -eq 8081 ]; then
  TARGET_GROUP_ARN=arn:aws:elasticloadbalancing:ap-northeast-2:661687591703:targetgroup/bbok-target-group-8081/0d8134e7b84fba6c
elif [ ${TARGET_PORT} -eq 8082 ]; then
  TARGET_GROUP_ARN=arn:aws:elasticloadbalancing:ap-northeast-2:661687591703:targetgroup/bbok-target-group-8082/f0af32f1a96da231
fi

aws elbv2 modify-listener --listener-arn arn:aws:elasticloadbalancing:ap-northeast-2:661687591703:listener/app/bbok/41179725cdb0e230/579c6a5212978fbc --default-actions Type=forward,TargetGroupArn=${TARGET_GROUP_ARN}
echo "Done."


# 함수로 타겟 그룹의 상태를 확인하고, 해당 타겟 그룹이 healthy한지 검사하는 함수 정의
check_target_group_health() {
  local target_group_arn=$1

  # aws elbv2 describe-target-health 명령어를 사용하여 타겟 그룹의 상태 정보를 JSON 형식으로 받아옴
  local target_health_json=$(aws elbv2 describe-target-health --target-group-arn $target_group_arn --region ap-northeast-2)

  # target-health를 파싱하여 인스턴스 상태 확인
  local health_status=$(echo $target_health_json | jq -r '.TargetHealthDescriptions[0].TargetHealth.State')

  # 타겟 그룹의 상태 출력
  echo "Target Group ARN: $target_group_arn"
  echo "Target Group Health Status: $health_status"

  # target-health 상태가 healthy이면 1을 반환, 아니면 0을 반환
  if [ "$health_status" = "healthy" ]; then
    return 1
  else
    return 0
  fi
}


# 타겟 그룹의 상태 확인 및 healthy 여부 검사
echo "Check target group status"
for RETRY_COUNT in 1 2 3 4 5 6 7 8 9 10
do
    echo "> #${RETRY_COUNT} trying..."
    check_target_group_health $TARGET_GROUP_ARN
	if [ $? -eq 1 ]; then
        echo "> Target Group is healthy."
		break
    elif [ ${RETRY_COUNT} -eq 10 ]; then
        echo "> Health check failed."
        exit 1
    fi
    sleep 10
done


# Healthy한게 확인되었다면 기존 서버 종료
echo "STOP ${CURRENT_PORT} Server.."
sudo systemctl stop bbok-${CURRENT_PORT}
echo "Deploy Success!"
exit 0