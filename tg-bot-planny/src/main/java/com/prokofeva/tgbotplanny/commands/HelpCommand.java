package com.prokofeva.tgbotplanny.commands;

import org.springframework.stereotype.Service;

@Service
public class HelpCommand implements Command {
    @Override
    public String execute(String msg) {
        return "Чем помочь?";
    }
}
