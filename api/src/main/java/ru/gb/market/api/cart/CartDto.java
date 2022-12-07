package ru.gb.market.api.cart;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;
@Schema(description = "Модель корзины")
public class CartDto {
    @Schema(description = "Список продуктов в корзине", required = true)
    private  List<CartItemDto> items;
    @Schema(description = "Общая стоимость продуктов в корзине", required = true)
    private BigDecimal totalPrice;

    public CartDto(List<CartItemDto> items, BigDecimal totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartDto() {

    }
}
