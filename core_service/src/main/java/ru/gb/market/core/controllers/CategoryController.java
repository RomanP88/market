package ru.gb.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.market.core.dto.CategoryDto;
import ru.gb.market.core.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/")
    public List<CategoryDto> findAll() {
        return categoryService.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
    }
}
