package ru.gb.market.api.exception;

public class ResourceNotFoundException extends RuntimeException {//исключение бросаемое если какой-то ресурс не найден
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
