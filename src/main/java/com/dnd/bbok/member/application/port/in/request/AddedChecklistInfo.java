package com.dnd.bbok.member.application.port.in.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddedChecklistInfo {

    @ApiModelProperty(name = "기준")
    private String criteria;

    @ApiModelProperty(name = "사용 여부")
    private Boolean isUsed;

}
