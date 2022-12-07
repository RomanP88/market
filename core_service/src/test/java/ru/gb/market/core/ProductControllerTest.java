package ru.gb.market.core;

import net.javacrumbs.jsonunit.JsonAssert;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.jdbc.Sql;
import ru.gb.market.api.core.ProductDto;
import ru.gb.market.api.dto.PageDto;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest extends FunctionalTest {

    @Test
    @Sql("restartDB.sql")
    void getByIdProduct() {
        ProductDto productDto = createRequest()
                .url("/api/v1/products/1")
                .get(ProductDto.class);
//                restTemplate.getForObject("/api/v1/products/1", ProductDto.class);
        String expected = getResource("testGetProductById/expected.json");
        JsonAssert.assertJsonEquals(
                productDto,
                expected,
                JsonAssert.when(Option.IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    @Sql("restartDB.sql")
    void getAllProducts() {
        PageDto<ProductDto> pageDto = createRequest()
                .url("/api/v1/products/?page=3")
                .get(new ParameterizedTypeReference<>() {
                });
//                restTemplate.exchange("/api/v1/products/?page=3",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<PageDto<ProductDto>>(){}).getBody();
        Assertions.assertNotNull(pageDto);
        Assertions.assertEquals(pageDto.getPageSize(), 10);
        Assertions.assertEquals(pageDto.getTotalElements(), 21);
        Assertions.assertEquals(pageDto.getContent().size(), 1);
    }

    @Test
    @Sql("restartDB.sql")
    void addProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setCategoryTitle("Fruit");
        productDto.setTitle("Apple");
        productDto.setPrice(new BigDecimal(100));
        createRequest()
                .url("/api/v1/products/createProduct")
                .post(ProductDto.class, productDto);
        PageDto<ProductDto> pageDto = createRequest()
                .url("/api/v1/products/?page=3")
                .get(new ParameterizedTypeReference<>() {
                });
        Assertions.assertEquals(pageDto.getTotalElements(), 22);
        Assertions.assertEquals(pageDto.getContent().get(1).getPrice(), BigDecimal.valueOf(100));
    }

    @Test
    @Sql({"restartDB.sql"})
    void deleteProduct() {
        createRequest()
                .url("/api/v1/products/5")
                .delete();
        PageDto<ProductDto> pageDto = createRequest()
                .url("/api/v1/products/")
                .get(new ParameterizedTypeReference<>() {
                });
        Assertions.assertEquals(pageDto.getTotalElements(), 20);
    }

    @Test
    @Sql("restartDB.sql")
    void updateProduct() {
        ProductDto productDto = createRequest()
                .url("/api/v1/products/1")
                .get(ProductDto.class);
        productDto.setCategoryTitle("Fruit");
        productDto.setTitle("Apple");
        productDto.setPrice(new BigDecimal(100));
        createRequest()
                .url("/api/v1/products/")
                .put(productDto);
        ProductDto productDtoAfterTest = createRequest()
                .url("/api/v1/products/1")
                .get(ProductDto.class);
        Assertions.assertEquals(productDtoAfterTest, productDto);
        Assertions.assertEquals(productDtoAfterTest.getPrice(), BigDecimal.valueOf(100));
    }
}
