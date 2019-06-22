package org.sandbox.utils;

import org.apache.shiro.session.Session;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.session.TelegramLongPollingSessionBot;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class NotifierBot extends TelegramLongPollingSessionBot {

    @Override
    public void onUpdateReceived(Update update, Optional<Session> optionalSession) {
        WeAreCaringScrapper scrapper = new WeAreCaringScrapper();

        SendMessage message = null;

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equalsIgnoreCase("start")) {
            if (optionalSession.isPresent()) {
                optionalSession.get().setAttribute("chatid", update.getMessage().getChatId());
            }
            message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .enableMarkdown(true)
                    .setReplyMarkup(KeyboardFactory.getCountryOptions())
                    .setText("Choose the nationality of the helper");
        }

        if (update.hasCallbackQuery() && optionalSession.isPresent()) {
            System.out.println("Session present");
            Session session = optionalSession.get();

            if (update.getCallbackQuery().getData().contains("|")) {
                String[] splits = update.getCallbackQuery().getData().split(Pattern.quote("|"));
                session.setAttribute(splits[0], splits[1]);
            }

            if (session.getAttributeKeys().contains("salary")) {
                message = new SendMessage()
                        .setChatId(String.valueOf(session.getAttribute("chatid")))
                        .enableMarkdown(true)
                        .setReplyMarkup(KeyboardFactory.getCountryOptions())
                        .setText("Choose the nationality of the helper");
            }

            if (session.getAttributeKeys().contains("country")) {
                message = new SendMessage()
                        .setChatId(String.valueOf(session.getAttribute("chatid")))
                        .enableMarkdown(true)
                        .setReplyMarkup(KeyboardFactory.getSalaryOptions())
                        .setText("Choose the max salary range for the helper");
            }

            //final state
            if (session.getAttributeKeys().containsAll(Arrays.asList("country", "salary"))) {
                session.setAttribute("stop", true);
                message = new SendMessage()
                        .setChatId(String.valueOf(session.getAttribute("chatid")))
                        .enableMarkdown(true)
                        .setText(scrapper.fetchEligibleHelpers(String.valueOf(session.getAttribute("country")), String.valueOf(session.getAttribute("salary"))));
            }

        }


        try {
            if (message != null) {
                execute(message);
            }
            if (optionalSession.isPresent() && optionalSession.get().getAttributeKeys().contains("stop") &&
                    Boolean.valueOf(optionalSession.get().getAttribute("stop") == Boolean.TRUE)) {
                optionalSession.get().stop();
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "ThozhaBot";
    }

    @Override
    public String getBotToken() {
        return "";
    }
}
