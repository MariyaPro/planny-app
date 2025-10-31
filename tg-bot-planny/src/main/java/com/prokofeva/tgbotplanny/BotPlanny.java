package com.prokofeva.tgbotplanny;

import com.prokofeva.tgbotplanny.commands.CommandFactory;
import com.prokofeva.tgbotplanny.facade.ReportFacade;
import com.prokofeva.tgbotplanny.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Setter
@RequiredArgsConstructor
public class BotPlanny extends TelegramLongPollingBot {

    private final CommandFactory commandFactory;
    private final ReportFacade reportFacade;
    private String name;
    private String token;

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            log.info("Callback query: {}", Util.toJson(update.getCallbackQuery()));
            var userId = update.getCallbackQuery().getFrom().getId();
            var report = reportFacade.getReport(update.getCallbackQuery());
            sendText(userId, report);
        } else {
            var msg = update.getMessage();
            var user = msg.getFrom();
            var id = user.getId();
            log.info("incoming message: {}", Util.toJson(msg));
            if (msg.isCommand()) {
                commandFactory.getCommand(msg.getText()).execute(this, id, msg.getText());
            } else sendText(id, "ок");
        }
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendKeyboard(Long who, ReplyKeyboard keyboard, String what) {
        var sm = SendMessage.builder()
                .text(what)
                .replyMarkup(keyboard)
                .chatId(who.toString())
                .build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
