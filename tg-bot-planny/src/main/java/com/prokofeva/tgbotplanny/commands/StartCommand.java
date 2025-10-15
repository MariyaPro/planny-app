package com.prokofeva.tgbotplanny.commands;

import org.springframework.stereotype.Service;

@Service
public class StartCommand implements Command {
    @Override
    public String execute(String msg) {
        return "Начнем!";
    }
}
