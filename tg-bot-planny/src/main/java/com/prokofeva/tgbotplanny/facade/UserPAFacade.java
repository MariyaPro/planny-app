package com.prokofeva.tgbotplanny.facade;

import com.prokofeva.tgbotplanny.client.DbUserPAClient;
import com.prokofeva.tgbotplanny.dto.UserPADto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPAFacade {
    private final DbUserPAClient dbUserPAClient;

    public void checkAndUpdateUser(User user) {
        if (dbUserPAClient.existsUserTg(user.getId()))
            return;
        var userPADto = UserPADto.builder()
                .idTg(user.getId())
                .firstNameTg(user.getFirstName())
                .lastNameTg(user.getLastName())
                .nameForReport(user.getFirstName() + " " + user.getLastName())
                .isBot(user.getIsBot())
                .userNameTg(user.getUserName())
                .languageCode(user.getLanguageCode())
                .subscriptionsId(new ArrayList<>())
                .build();
        dbUserPAClient.saveUser(userPADto);
    }

}