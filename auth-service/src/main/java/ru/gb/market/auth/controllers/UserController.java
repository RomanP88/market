package ru.gb.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.exception.DataValidationException;
import ru.gb.market.auth.entities.User;
import ru.gb.market.auth.services.UserService;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated User user, BindingResult bindingResult) {//создаем пользователя
        if (bindingResult.hasErrors()) {//если валидация не пройдена, бросаем ошибка в которой будет список ошибок связанных с неккоретным вводом данных пользователя
            throw new DataValidationException(bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        if(userService.existsByUsername(user.getUsername())){// проверка на существование имень пользователя в базе данных
            throw new DataValidationException("A user with this username exists");
        }
        return userService.save(user);
    }
}
