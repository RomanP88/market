package ru.gb.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.dto.PageDto;
import ru.gb.market.api.exception.ResourceNotFoundException;
import ru.gb.market.api.exception.DataValidationException;
import ru.gb.market.core.entities.Product;
import ru.gb.market.core.services.ProductService;
import ru.gb.market.api.core.ProductDto;
import ru.gb.market.core.mapper.product.ProductMapper;

import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;

    @GetMapping
    public PageDto<ProductDto> findAll(@RequestParam(name = "page", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        PageDto<Product> temp = productService.findAll(pageIndex - 1, 10);
        return temp.convert(temp, mapper::mapToDto);
    }
//    @GetMapping("/")
//    public Page<ProductDto> findAllProduct(@RequestParam(name = "page", defaultValue = "1") int pageIndex) {
//        if (pageIndex < 1) {
//            pageIndex = 1;
//        }
//        return productService.findAllProduct(pageIndex - 1, 10).map(mapper::mapToDto);
//    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return mapper.mapToDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found")));
    }


    @PostMapping("/createProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto save(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        Product product = mapper.mapFromDto(productDto);
        product.setCount(1);
        productService.save(product);
        return mapper.mapToDto(product);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ProductDto productDto) {
        productService.updateProductFromDto(productDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
