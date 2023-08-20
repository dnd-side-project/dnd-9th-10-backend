package com.dnd.bbok.friend.application.port.in.response;

import com.dnd.bbok.friend.domain.FriendTag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFriendTagsResponse {
    @ApiModelProperty(value = "태그 목록")
    private final List<FriendTag> tags;

    public GetFriendTagsResponse(List<FriendTag> tags) {
        this.tags = tags;
    }
}
