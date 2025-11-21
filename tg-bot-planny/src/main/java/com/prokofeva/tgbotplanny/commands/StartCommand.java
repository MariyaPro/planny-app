package com.prokofeva.tgbotplanny.commands;

import com.prokofeva.tgbotplanny.BotPlanny;
import com.prokofeva.tgbotplanny.facade.UserPAFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
@Slf4j
public class StartCommand implements Command {
    private final UserPAFacade userPAFacade;

    @Override
    public void execute(BotPlanny botPlanny, Update update) {
        var user = update.getMessage().getFrom();
        var msg = String.format("Добрый день, %s!", user.getFirstName());
        botPlanny.sendText(user.getId(), msg);
        if (userPAFacade.checkAndUpdateUser(user)) {
            botPlanny.sendText(user.getId(), "Я Вас раньше не встречала.");
        }
    }

}
