package ru.gb.market.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.market.core.entities.Category;
import ru.gb.market.core.entities.Product;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;
    private List<Product> products;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.products = getProducts();
    }
}
