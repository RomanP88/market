package ru.gb.market.core.mapper.product;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.market.api.core.ProductDto;
import ru.gb.market.core.entities.Product;
import ru.gb.market.core.services.CategoryService;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ProductMapper {

    @Autowired
    protected CategoryService categoryService;

    @Mapping(target = "categoryTitle", source = "category.title")//аннотация означает что в поле ProductDto.categoryTitle будет мапится стринговое значение из category.title
    public abstract ProductDto mapToDto(Product product);

    @Mapping(target = "category", expression = "java(categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new RuntimeException(\"Category title = \" + productDto.getCategoryTitle() + \" not found\")))")
    public abstract Product mapFromDto(ProductDto productDto);
}
