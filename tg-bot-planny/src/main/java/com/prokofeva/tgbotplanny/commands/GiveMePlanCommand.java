package com.prokofeva.tgbotplanny.commands;

import com.prokofeva.tgbotplanny.BotPlanny;
import com.prokofeva.tgbotplanny.enums.ReportTypeCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GiveMePlanCommand implements Command {

    @Override
    public void execute(BotPlanny botPlanny, Long userId, String msg) {
        askParam(botPlanny, userId);
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
//                .keyboardRow(keyboardIn)
                .build();

       botPlanny.sendKeyboard(userId, inlineKeyboardMarkup);
    }

}
