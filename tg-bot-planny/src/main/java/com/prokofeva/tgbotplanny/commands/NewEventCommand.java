package com.prokofeva.tgbotplanny.commands;

import com.prokofeva.tgbotplanny.BotPlanny;
import org.springframework.stereotype.Service;

@Service
public class NewEventCommand implements Command {
    @Override
    public void execute(BotPlanny botPlanny, Long userId, String msg) {
         botPlanny.sendText(userId,"Я пока не умею, но скоро научусь.");
    }
}
