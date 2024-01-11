package com.example.data_api.api.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;


@Builder
public record NotificationDto(@JsonProperty("included_segments")
                              String[] includedSegments,
                              ContentDto contents,
                              @JsonProperty("app_id")
                              String appId
                              ) {
}
