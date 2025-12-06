package com.prokofeva.tgbotplanny.commands;

import com.prokofeva.tgbotplanny.BotPlanny;
import com.prokofeva.tgbotplanny.enums.ReportTypeCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GiveMePlanCommand implements Command {
    @Value("${users.allowed-to-read}")
    private String readers;

    @Override
    public void execute(BotPlanny botPlanny, Update update) {
        var userId = update.getMessage().getFrom().getId();
        if (checkPermissions(userId)) {
            askParam(botPlanny, userId);
            return;
        }
        botPlanny.sendText(userId, "You don't have permission to do this!");
    }

    private boolean checkPermissions(long userId) {
        return readers.contains(String.valueOf(userId));
    }

    public void askParam(BotPlanny botPlanny, Long userId) {
        var keyboardIn = Arrays.stream(ReportTypeCode.values())
                .map(m -> InlineKeyboardButton.builder()
                        .text(m.getDescription())
                        .callbackData(m.name())
                        .build())
                .toList();
        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
                .clearKeyboard()
                .keyboard(List.of(keyboardIn))
                .build();

        botPlanny.sendKeyboard(userId, inlineKeyboardMarkup, "какой план тебе нужен?");
    }

}
