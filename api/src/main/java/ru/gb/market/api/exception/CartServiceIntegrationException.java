package ru.gb.market.api.exception;

public class CartServiceIntegrationException extends RuntimeException{ //ошибка возникающая в "core-service" при работе с корзиной
    public CartServiceIntegrationException(String message) {
        super(message);
    }
}
