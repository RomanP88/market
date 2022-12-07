package ru.gb.market.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.market.api.core.ProductDto;
import ru.gb.market.cart.entities.Cart;
import ru.gb.market.api.exception.ResourceNotFoundException;
import ru.gb.market.cart.integrations.ProductServiceIntegration;

import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;
    @Value("${utils.cart.prefix}")//Шаблон для ключа в Redis, маска WEB_MARKET_XXX, где XXX-это либо username пользователя, либо UUID если пользователь не авторизован.
    private String cartPrefix;

    public String generateCartUuid() {//генерация случайного UUID
        return UUID.randomUUID().toString();
    }

    public String getCartUuidFromSuffix(String suffix) {//Получение ключа для работы с Redis (WEB_MARKET_XXX)
        return cartPrefix + suffix;
    }

    public Cart getCurrentCart(String cartKey) {// получение Корзины из Redis, если корзины нету, то создаем.
        if (!redisTemplate.hasKey(cartKey)) {
            redisTemplate.opsForValue().set(cartKey, new Cart());//добавление корзины в Redis
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);//получение корзины из Redis
    }

    public void addToCart(String cartKey, Long productId) {//Добавление продукта в корзину
        ProductDto productDto = productServiceIntegration.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину. Продукт не найдет, id: " + productId));
        execute(cartKey, c ->
                c.add(productDto)//реализация функционального интерфейса (добавление продукта в корзину)
        );
    }

    public void clearCart(String cartKey) {//метод очистки корзины
        execute(cartKey, c->
                c.clear()//реализация функционального интерфейса (очистка корзины)
        );
    }

    public void removeItemFromCart(String cartKey, Long productId) {
        execute(cartKey, c ->
                c.remove(productId)//реализация функционального интерфейса (удаление продукта из корзину)
        );
    }

    public void decrementItem(String cartKey, Long productId) {
        execute(cartKey, c ->
                c.decrement(productId)//реализация функционального интерфейса (уменьшение количества продукта в корзине по Id продукта)
        );
    }

    public void merge(String userCartKey, String guestCartKey) {//метод для слияния корзин
        Cart guestCart = getCurrentCart(guestCartKey);//получение гостевой корзины по UUID
        Cart userCart = getCurrentCart(userCartKey);//получение корзины авторизованного пользователя
        userCart.merge(guestCart);//метод для слияния корзин
        updateCart(guestCartKey, guestCart);//обновление гостевой корзины в Redis
        updateCart(userCartKey, userCart);//обновление корзины авторизованного пользователя в Redis
    }

    private void execute(String cartKey, Consumer<Cart> action) { //паттерн Шаблонный метод
        Cart cart = getCurrentCart(cartKey);// получаем корзину из Redis
        action.accept(cart);//реализация метода функционального интерфейса Consumer<>.
        redisTemplate.opsForValue().set(cartKey, cart);//обновление корзины в Redis
    }

    public void updateCart(String cartKey, Cart cart) {//метод обновление корзины в Redis
        redisTemplate.opsForValue().set(cartKey, cart);
    }
}
