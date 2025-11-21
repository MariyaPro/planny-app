package com.prokofeva.tgbotplanny.commands;

import com.prokofeva.tgbotplanny.BotPlanny;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class HelpCommand implements Command {

    @Override
    public void execute(BotPlanny botPlanny, Update update) {
        var userId = update.getMessage().getFrom().getId();
        botPlanny.sendText(userId, "Чем помочь?");
    }
}
