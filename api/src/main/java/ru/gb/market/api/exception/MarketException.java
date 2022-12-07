package ru.gb.market.api.exception;


import java.util.Date;

public class MarketException {//класс для описания ошибки
    private String messages;
    private String code;
    private Date date;

    public MarketException(String messages, String code) {
        this.messages = messages;
        this.code = code;
        this.date = new Date();
    }

    public MarketException(String messages) {
        this.messages = messages;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MarketException() {
    }
}
