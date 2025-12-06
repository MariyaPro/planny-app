package com.prokofeva.dbuserpaservice.mapper;

import com.prokofeva.dbuserpaservice.dto.UserPADto;
import com.prokofeva.dbuserpaservice.entity.UserPA;

import java.util.ArrayList;

public class MapperUserPA {

    public static UserPA toEntity(UserPADto dto) {
        return UserPA.builder()
                ._id(dto._id())
                .idTg(dto.idTg())
                .firstNameTg(dto.firstNameTg())
                .lastNameTg(dto.lastNameTg())
                .nameForReport(dto.nameForReport())
                .isBot(dto.isBot())
                .userNameTg(dto.userNameTg())
                .languageCode(dto.languageCode())
                .subscriptionsId(new ArrayList<>(dto.subscriptionsId()))
                .build();
    }

    public static UserPADto toDto(UserPA entity) {
        return UserPADto.builder()
                ._id(entity.get_id())
                .idTg(entity.getIdTg())
                .firstNameTg(entity.getFirstNameTg())
                .lastNameTg(entity.getLastNameTg())
                .nameForReport(entity.getNameForReport())
                .isBot(entity.isBot())
                .userNameTg(entity.getUserNameTg())
                .languageCode(entity.getLanguageCode())
                .subscriptionsId(entity.getSubscriptionsId())
                .build();
    }
}
