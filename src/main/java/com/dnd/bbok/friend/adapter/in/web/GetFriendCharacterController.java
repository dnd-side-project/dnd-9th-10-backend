package com.dnd.bbok.friend.adapter.in.web;

import com.dnd.bbok.friend.application.port.in.usecase.GetIconQuery;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "친구 관련 컨트롤러")
@RequiredArgsConstructor
public class GetFriendCharacterController {

  private final GetIconQuery getIconQuery;
  //TODO: 컨트롤러 추가하기

}
