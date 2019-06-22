package org.sandbox.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();
            botsApi.registerBot(new NotifierBot());
            System.out.println("Bot registered");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
