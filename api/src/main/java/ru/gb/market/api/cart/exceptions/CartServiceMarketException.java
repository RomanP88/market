package ru.gb.market.api.cart.exceptions;

import ru.gb.market.api.exception.MarketException;

public class CartServiceMarketException extends MarketException {
    public enum CartServiceExceptions { // перечисления для ошибок которые могут возникнуть в сервисе корзины
        CART_IS_BROKEN, CART_ID_GENERATOR_DISABLED, CART_NOT_FOUND
    }

    public CartServiceMarketException(String code, String message) {
        super(code, message);
    }
}
