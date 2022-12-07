package ru.gb.market.api.core;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Objects;


public class ProductDto {
    private Long id;
    @NotNull(message = "The product must have a name")
    @Length(min = 3, max = 255, message = "The length of the product name should be 3-255 characters")
    private String title;
    @Min(value = 1, message = "The price of the product must be at least 1 rub")
    private BigDecimal price;
    @NotNull(message = "The product must have a category")
    private String categoryTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public ProductDto(Long id, String title, BigDecimal price, String categoryTitle) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categoryTitle = categoryTitle;
    }

    public ProductDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return price.equals(that.price) && Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(categoryTitle, that.categoryTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, categoryTitle);
    }
}
