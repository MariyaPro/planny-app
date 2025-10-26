package com.prokofeva.tgbotplanny.configs;

import com.prokofeva.tgbotplanny.BotPlanny;
import com.prokofeva.tgbotplanny.commands.CommandFactory;
import com.prokofeva.tgbotplanny.facade.ReportFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@PropertySource("${telegrambot.prop.path}")
public class BotPlannyConfig {
    @Value("${name}")
    private String name;
    @Value("${id}")
    private String id;
    @Value("${token}")
    private String token;

    @Bean
    public BotPlanny botPlanny(CommandFactory factory, ReportFacade reportFacade) throws TelegramApiException {
        BotPlanny botPlanny = new BotPlanny(factory, reportFacade);
        botPlanny.setName(name);
        botPlanny.setToken(token);

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(botPlanny);

        botPlanny.sendText(Long.parseLong(id), "Привет!");
        return botPlanny;
    }
}
