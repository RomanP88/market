package ru.gb.market.api.cart;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель продукта в корзине")
public class CartItemDto {
    @Schema(description = "Id продукта", required = true)
    private Long productId;
    @Schema(description = "Название продукта", required = true)
    private String productTitle;
    @Schema(description = "Количество", required = true)
    private int count;
    @Schema(description = "Стоимость за единицу", required = true)
    private BigDecimal pricePerProduct;
    @Schema(description = "Общая стоимость", required = true)
    private BigDecimal price;

    public CartItemDto(Long productId, String productTitle, int count, BigDecimal pricePerProduct, BigDecimal price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.count = count;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

    public CartItemDto() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
