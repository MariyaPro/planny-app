package com.prokofeva.tgbotplanny.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserPADto(
        String _id,
        Long idTg,
        String firstNameTg,
        String lastNameTg,
        Boolean isBot,
        String userNameTg,
        String languageCode,
        String ownerId,
        List<String> subscriptionsId
) {
}
