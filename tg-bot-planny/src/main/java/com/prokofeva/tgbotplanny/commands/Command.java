package com.prokofeva.tgbotplanny.commands;

import com.prokofeva.tgbotplanny.BotPlanny;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {

    void execute(BotPlanny botPlanny, Update update);
}
