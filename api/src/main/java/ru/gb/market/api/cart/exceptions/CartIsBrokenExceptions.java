package ru.gb.market.api.cart.exceptions;

public class CartIsBrokenExceptions extends RuntimeException{// ошибка возникающая при работе с корзиной
    public CartIsBrokenExceptions(String message) {
        super(message);
    }
}
