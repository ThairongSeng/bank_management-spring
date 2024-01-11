package com.example.data_api.api.notification;


import com.example.data_api.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationRestController {

    private final NotificationService notificationService;


    @PostMapping
    public BaseRest<?> pushNotification(@RequestBody CreateNotificationDto notificationDto){
        notificationService.pushNotification(notificationDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Notification has been pushed")
                .timestamp(LocalDateTime.now())
                .data(notificationDto.contents())
                .build();
    }
}
