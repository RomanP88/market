package ru.gb.market.api.exception;

import java.util.List;

public class DataValidationException extends RuntimeException {//исключение бросаемое при неправильной валидации данных
    private List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public DataValidationException(List<String> messages) {
        this.messages = messages;
    }
    public DataValidationException(String message) {
        this(List.of(message));
    }
}
