package com.prokofeva.tgbotplanny.mapper;

import com.prokofeva.tgbotplanny.dto.UserPADto;
import com.prokofeva.tgbotplanny.entity.UserPA;

import java.util.ArrayList;

public class MapperUserPA {

    public static UserPA toEntity(UserPADto dto) {
        return UserPA.builder()
                ._id(dto._id())
                .idTg(dto.idTg())
                .firstNameTg(dto.firstNameTg())
                .lastNameTg(dto.lastNameTg())
                .isBot(dto.isBot())
                .userNameTg(dto.userNameTg())
                .languageCode(dto.languageCode())
                .ownerId(dto.ownerId())
                .subscriptionsId(new ArrayList<>(dto.subscriptionsId()))
                .build();
    }

    public static UserPADto toDto(UserPA entity) {
        return UserPADto.builder()
                ._id(entity.get_id())
                .idTg(entity.getIdTg())
                .firstNameTg(entity.getFirstNameTg())
                .lastNameTg(entity.getLastNameTg())
                .isBot(entity.isBot())
                .userNameTg(entity.getUserNameTg())
                .languageCode(entity.getLanguageCode())
                .ownerId(entity.getOwnerId())
                .subscriptionsId(entity.getSubscriptionsId())
                .build();
    }
}
