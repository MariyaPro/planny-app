package com.prokofeva.tgbotplanny.commands;

import com.prokofeva.tgbotplanny.BotPlanny;
import org.springframework.stereotype.Service;

@Service
public class HelpCommand implements Command {
    @Override
    public void execute(BotPlanny botPlanny, Long userId, String msg) {
         botPlanny.sendText(userId,"Чем помочь?");
    }
}
