package com.prokofeva.tgbotplanny.client;

import com.prokofeva.tgbotplanny.dto.UserPADto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.telegram.telegrambots.meta.api.objects.User;

@FeignClient(
        name = "dbUserPAService",
        url = "${services.db-user-pa-service}"
)
public interface DbUserPAClient {

    @PostMapping("/")
    void checkAndUpdateUser(User user);

    @GetMapping("/users")
    boolean existsUserTg(@RequestParam ("tg") long id);

    @PostMapping("/")
    void saveUser(@RequestBody UserPADto userPADto);
}
