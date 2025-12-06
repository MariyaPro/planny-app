package com.prokofeva.tgbotplanny;

import com.prokofeva.tgbotplanny.commands.CommandFactory;
import com.prokofeva.tgbotplanny.facade.ReportFacade;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.prokofeva.tgbotplanny.util.Util.toJson;

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
            log.info("Callback query: {}", toJson(update.getCallbackQuery()));
            var userId = update.getCallbackQuery().getFrom().getId();
            var report = reportFacade.getReport(update.getCallbackQuery());
            sendText(userId, report);
        } else {
            var msg = update.getMessage();
            var id = msg.getFrom().getId();
            log.info("incoming message: {}", toJson(msg));
            if (msg.isCommand()) {
                commandFactory.getCommand(msg.getText()).execute(this, update);
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

    public void sendMenu(Long who, String txt, InlineKeyboardMarkup kb) {
        SendMessage sm = SendMessage.builder().chatId(who.toString())
                .parseMode("HTML").text(txt)
                .replyMarkup(kb)
                .build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
