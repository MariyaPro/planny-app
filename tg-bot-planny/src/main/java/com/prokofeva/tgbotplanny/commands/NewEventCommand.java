package com.prokofeva.tgbotplanny.commands;

import com.prokofeva.tgbotplanny.BotPlanny;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewEventCommand implements Command {
    @Value("${services.editor-planny-ui}")
    private String baseUrl;

    @Override
    public void execute(BotPlanny botPlanny, Update update) {
        var userId = update.getMessage().getFrom().getId();
        askParam(botPlanny, userId);
    }

    public void askParam(BotPlanny botPlanny, Long userId) {
        var keyboardIn = InlineKeyboardButton.builder()
                .text("форма")
                .url(baseUrl +"/events/new/"+userId)
                .build();
        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
                .clearKeyboard()
                .keyboardRow(List.of(keyboardIn))
                .build();

//        botPlanny.sendKeyboard(userId, inlineKeyboardMarkup, "заполни форму");
        botPlanny.sendMenu(userId,"Заполни форму",inlineKeyboardMarkup);
    }
}
