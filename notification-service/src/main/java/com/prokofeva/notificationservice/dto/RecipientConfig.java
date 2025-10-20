package com.prokofeva.notificationservice.dto;

import com.prokofeva.notificationservice.enums.MessageFormat;
import jakarta.validation.constraints.Email;

public record RecipientConfig(
        String deliveryMethod,
        MessageFormat messageFormat,
        @Email
        String email
) {
}
