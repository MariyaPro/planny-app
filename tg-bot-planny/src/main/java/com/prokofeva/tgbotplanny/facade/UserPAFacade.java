package com.prokofeva.tgbotplanny.facade;

import com.prokofeva.tgbotplanny.client.DbEventsClient;
import com.prokofeva.tgbotplanny.dto.OwnerDto;
import com.prokofeva.tgbotplanny.dto.UserPADto;
import com.prokofeva.tgbotplanny.entity.UserPA;
import com.prokofeva.tgbotplanny.service.UserPAService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;
import java.util.Objects;

import static com.prokofeva.tgbotplanny.util.Util.toJson;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPAFacade {
    private final UserPAService userPAService;
    private final DbEventsClient dbEventsClient;

    @Transactional
    public boolean checkAndUpdateUser(User user) {
        boolean isUpdated;
        var userPADto = userPAService.findUserPAByIdTg(user.getId());
        if (userPADto == null) {
            var userDb = createNewUserPA(user);
            isUpdated = true;
            log.info("New UserPA saved successfully ({})", toJson(userDb));
        } else {
            if (isChanged(user, userPADto)) {
                String ownerId =getOwnerId(user, userPADto);
                var userUpdated = UserPA.builder()
                        ._id(userPADto._id())
                        .idTg(user.getId())
                        .firstNameTg(user.getFirstName())
                        .lastNameTg(user.getLastName())
                        .isBot(user.getIsBot())
                        .userNameTg(user.getUserName())
                        .languageCode(user.getLanguageCode())
                        .ownerId(ownerId)
                        .subscriptionsId(userPADto.subscriptionsId())
                        .build();
                userPAService.save(userUpdated);
            }
            isUpdated = true;
        }
        return isUpdated;
    }

    private String getOwnerId(User user, UserPADto userPADto) {
        String ownerId;
        if (userPADto.ownerId() == null) {
            ownerId = createNewOwner(user);
            userPADto.subscriptionsId().add(ownerId);
        } else {
            ownerId = userPADto.ownerId();
        }
        return ownerId;
    }

    private boolean isChanged(User user, UserPADto userPADto) {
        return userPADto.ownerId() == null
               || !Objects.equals(user.getFirstName(), userPADto.firstNameTg())
               || !Objects.equals(user.getLastName(), userPADto.lastNameTg())
               || !Objects.equals(user.getIsBot(), userPADto.isBot())
               || !Objects.equals(user.getUserName(), userPADto.userNameTg())
               || !Objects.equals(user.getLanguageCode(), userPADto.languageCode());
    }

    private UserPADto createNewUserPA(User user) {
        var ownerId = createNewOwner(user);
        var userPA = UserPA.builder()
                .idTg(user.getId())
                .firstNameTg(user.getFirstName())
                .lastNameTg(user.getLastName())
                .isBot(user.getIsBot())
                .userNameTg(user.getUserName())
                .languageCode(user.getLanguageCode())
                .ownerId(ownerId)
                .subscriptionsId(List.of(ownerId))
                .build();
        return userPAService.save(userPA);
    }

    private String createNewOwner(User user) {
        var ownerDto = OwnerDto.builder()
                .name(user.getFirstName())
                .userIdTg(user.getId())
                .description(user.getFirstName() + " " + user.getLastName())
                .active(true)
                .build();
        var ownerId = dbEventsClient.createOwner(ownerDto).toString();
        log.info("New Owner created successfully ({})", ownerId);
        return ownerId;
    }

}
