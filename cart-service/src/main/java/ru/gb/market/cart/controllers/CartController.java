package ru.gb.market.cart.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.cart.CartDto;
import ru.gb.market.api.dto.StringResponse;
import ru.gb.market.cart.mapper.CartMapper;
import ru.gb.market.cart.services.CartService;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/cart")
@Tag(name = "Carts", description = "Methods for work with carts")
public class CartController {
    private final CartService cartService;
    private final CartMapper mapper;

    @Operation(
            summary = "Запрос на получение корзины",
            responses = {
                    @ApiResponse(
                            description = "Ответ успешный", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CartDto.class))
                    )
            }
    )
    @GetMapping("/{uuid}")//получение корзины из Redis
    public CartDto getCart(@RequestHeader(required = false)
                           @Parameter(description = "Уникальный набор символов для неавторизованного пользователя") String username,
                           @PathVariable
                           @Parameter(description = "Имя пользователя из header") String uuid) {//@RequestHeader(required = false) - достаем "username" из header
        return mapper.mapToDto(cartService.getCurrentCart(getCurrentCartUuid(username, uuid)));
    }

    @Operation(
            summary = "Генератор случайного набора символов для неавторизованного пользователя",
            responses = {
                    @ApiResponse(
                            description = "Ответ успешный", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    @GetMapping("/generate")
//При входе на сайт, пользователю создается случайный набор символов UUID, это будет индитифакор корзины неавторизованного пользователя в Redis.
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());//сделал класс StringResponse. только ради того что бы передавались данные в формате JSON.
    }

    @Operation(
            summary = "Добавление продукта в корзину, если username = null, то добавляем в корзину по UUID, в противном случае добавляем в корзину пользователя",
            responses = {
                    @ApiResponse(
                            description = "Ответ успешный", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/add/{productId}")//добавление продукта в корзину по Id продукта
    public void add(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }
    @Operation(
            summary = "Уменьшение количества продукта в корзине на 1, если username = null, то работаем с корзиной по UUID, в противном случае работаем с корзиной пользователя",
            responses = {
                    @ApiResponse(
                            description = "Ответ успешный", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/decrement/{productId}")//уменьшение количества продукта в корзине по Id продукта
    public void decrement(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }
    @Operation(
            summary = "Удаление продукта из корзины по Id продукта, если username = null, то работаем с корзиной по UUID, в противном случае работаем с корзиной пользователя",
            responses = {
                    @ApiResponse(
                            description = "Ответ успешный", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/remove/{productId}")//удаление продукта в корзине по Id продукта
    public void remove(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }
    @Operation(
            summary = "Очистка корзины, если username = null, то работаем с корзиной по UUID, в противном случае работаем с корзиной пользователя",
            responses = {
                    @ApiResponse(
                            description = "Ответ успешный", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/clear")//очистка корзины
    public void clear(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }
    @Operation(
            summary = "Слияние корзин при авторизации пользователя, если перед авторизацией в корзине были продукты, они добавятся к корзину пользователя",
            responses = {
                    @ApiResponse(
                            description = "Ответ успешный", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/merge")//слияние двух корзин
    public void merge(@RequestHeader String username, @PathVariable String uuid) {
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(String username, String uuid) {//получение ключа для получения данных из Redis
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
