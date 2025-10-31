package com.prokofeva.tgbotplanny.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommandFactory {
    private final GiveMePlanCommand giveMePlanCommand;
    private final StartCommand startCommand;
    private final HelpCommand helpCommand;
    private final NewEventCommand newEventCommand;

    public Command getCommand(String commandName) {
        return switch (commandName) {
            case "/start" -> startCommand;
            case "/help" -> helpCommand;
            case "/give_me_plan" -> giveMePlanCommand;
            case "/new_event" -> newEventCommand;
            default -> null;
        };
    }
}
