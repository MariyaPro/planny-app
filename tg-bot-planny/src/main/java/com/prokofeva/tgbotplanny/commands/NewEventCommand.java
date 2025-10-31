package com.prokofeva.tgbotplanny.commands;

import com.prokofeva.tgbotplanny.BotPlanny;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Service
public class NewEventCommand implements Command {
    @Override
    public void execute(BotPlanny botPlanny, Long userId, String msg) {
        askParam(botPlanny, userId);
    }

    public void askParam(BotPlanny botPlanny, Long userId) {
        var keyboardIn = InlineKeyboardButton.builder()
                .text("форма")
                .url("http://localhost:8085/api/planny/events/new")
                .build();
        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
                .clearKeyboard()
                .keyboardRow(List.of(keyboardIn))
                .build();

        botPlanny.sendKeyboard(userId, inlineKeyboardMarkup, "заполни форму");
    }
}
