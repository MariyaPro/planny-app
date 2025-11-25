package com.prokofeva.dbuserpaservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.util.List;

@Builder
public record UserPADto(
        String _id,
        Long idTg,
        String firstNameTg,
        String lastNameTg,
        @NotNull
        String nameForReport,
        Boolean isBot,
        String userNameTg,
        String languageCode,
        List<String> subscriptionsId
) {
}
