package com.prokofeva.notificationservice.dto;

import jakarta.validation.constraints.Email;

public record RecipientConfig(
        String deliveryMethod,
        String messageFormat,
        @Email
        String email
) {
}
