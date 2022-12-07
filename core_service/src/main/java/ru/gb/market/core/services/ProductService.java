package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.api.dto.PageDto;
import ru.gb.market.api.exception.ResourceNotFoundException;
import ru.gb.market.api.core.ProductDto;
import ru.gb.market.core.entities.Category;
import ru.gb.market.core.entities.Product;
import ru.gb.market.core.repositories.ProductRepository;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    //    public Page<Product> findAllProduct(int pageIndex, int pageSize) {
//        return productRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by("id").ascending()));
//    }
    public PageDto<Product> findAll(int pageIndex, int pageSize) {
        return new PageDto<>(pageIndex, pageSize, productRepository.findAll());
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProductFromDto(ProductDto productDto) {
        Product product = findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product id = " + productDto.getId() + " not found"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category title = " + productDto.getCategoryTitle() + " not found"));
        product.setCategory(category);
    }
}
