package com.prokofeva.tgbotplanny.commands;

import org.springframework.stereotype.Service;

@Service
public class GiveMePlanCommand implements Command {
    @Override
    public String execute(String msg) {
        return "вот тебе план на неделю: ...";
    }
}
