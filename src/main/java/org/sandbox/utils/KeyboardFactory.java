package org.sandbox.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class KeyboardFactory {

    public static ReplyKeyboard getCountryOptions() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Filipino").setCallbackData("country|ph"));
        rowInline.add(new InlineKeyboardButton().setText("Indonesian").setCallbackData("country|id"));
        rowInline.add(new InlineKeyboardButton().setText("Sri Lankan").setCallbackData("country|lk"));
        rowsInline.add(rowInline);
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        return inlineKeyboardMarkup;
    }

    public static ReplyKeyboard getSalaryOptions() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("550").setCallbackData("salary|550"));
        rowInline.add(new InlineKeyboardButton().setText("600").setCallbackData("salary|600"));
        rowInline.add(new InlineKeyboardButton().setText("650").setCallbackData("salary|650"));
        rowInline.add(new InlineKeyboardButton().setText("700").setCallbackData("salary|700"));
        rowInline.add(new InlineKeyboardButton().setText("750").setCallbackData("salary|750"));
        rowInline.add(new InlineKeyboardButton().setText("800").setCallbackData("salary|800"));
        rowInline.add(new InlineKeyboardButton().setText("850").setCallbackData("salary|850"));
        rowInline.add(new InlineKeyboardButton().setText("900").setCallbackData("salary|900"));
        rowInline.add(new InlineKeyboardButton().setText("1000").setCallbackData("salary|1000"));
        rowsInline.add(rowInline);
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        return inlineKeyboardMarkup;
    }
}
