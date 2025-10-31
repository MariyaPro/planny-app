package com.prokofeva.tgbotplanny.commands;

import com.prokofeva.tgbotplanny.BotPlanny;

public interface Command {

    void execute(BotPlanny botPlanny, Long id, String msg);
}
