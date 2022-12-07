package ru.gb.market.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gb.market.api.exception.*;

@ControllerAdvice//объявляем глобальный обработчик исключений
public class GlobalExceptionHandler {
    @ExceptionHandler//аннотация указывает что мы тут будем обрабатывать все исключения типа "ResourceNotFoundException"
    private ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new MarketException("RESOURCE_NOT_FOUND_EXCEPTION",e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler//аннотация указывает что мы тут будем обрабатывать все исключения типа "CartServiceIntegrationException"
    private ResponseEntity<?> catchCartServiceIntegrationException(CartServiceIntegrationException e) {
        return new ResponseEntity<>(new MarketException("CART_SERVICE_INTEGRATION_EXCEPTION",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler//аннотация указывает что мы тут будем обрабатывать все исключения типа "DataValidationException"
    private ResponseEntity<?> catchDataValidationException(DataValidationException e) {
        return new ResponseEntity<>(new DataValidationException(e.getMessages()), HttpStatus.BAD_REQUEST);
    }
}
