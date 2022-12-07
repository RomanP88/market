package ru.gb.market.order.tempForOpenApiGenerateFiles.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * OrderItem
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-07T22:53:55.526326900+03:00[Europe/Moscow]")
public class OrderItems {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("productTitle")
  private String productTitle;

  @JsonProperty("count")
  private Integer count;

  @JsonProperty("pricePerProduct")
  private Integer pricePerProduct;

  @JsonProperty("totalPrice")
  private Integer totalPrice;

  public OrderItems id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderItems productTitle(String productTitle) {
    this.productTitle = productTitle;
    return this;
  }

  /**
   * Get productTitle
   * @return productTitle
  */
  
  @Schema(name = "productTitle", required = false)
  public String getProductTitle() {
    return productTitle;
  }

  public void setProductTitle(String productTitle) {
    this.productTitle = productTitle;
  }

  public OrderItems count(Integer count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * @return count
  */
  
  @Schema(name = "count", required = false)
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public OrderItems pricePerProduct(Integer pricePerProduct) {
    this.pricePerProduct = pricePerProduct;
    return this;
  }

  /**
   * Get pricePerProduct
   * @return pricePerProduct
  */
  
  @Schema(name = "pricePerProduct", required = false)
  public Integer getPricePerProduct() {
    return pricePerProduct;
  }

  public void setPricePerProduct(Integer pricePerProduct) {
    this.pricePerProduct = pricePerProduct;
  }

  public OrderItems totalPrice(Integer totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  /**
   * Get totalPrice
   * @return totalPrice
  */
  
  @Schema(name = "totalPrice", required = false)
  public Integer getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Integer totalPrice) {
    this.totalPrice = totalPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderItems orderItem = (OrderItems) o;
    return Objects.equals(this.id, orderItem.id) &&
        Objects.equals(this.productTitle, orderItem.productTitle) &&
        Objects.equals(this.count, orderItem.count) &&
        Objects.equals(this.pricePerProduct, orderItem.pricePerProduct) &&
        Objects.equals(this.totalPrice, orderItem.totalPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productTitle, count, pricePerProduct, totalPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderItem {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    productTitle: ").append(toIndentedString(productTitle)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    pricePerProduct: ").append(toIndentedString(pricePerProduct)).append("\n");
    sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

