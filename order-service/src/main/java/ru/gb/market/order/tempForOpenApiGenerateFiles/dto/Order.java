package ru.gb.market.order.tempForOpenApiGenerateFiles.dto;

import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Order
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-07T22:53:55.526326900+03:00[Europe/Moscow]")
public class Order {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("username")
  private String username;

  @JsonProperty("address")
  private String address;

  @JsonProperty("phone")
  private String phone;

  @JsonProperty("listOrderItems")
  @Valid
  private List<OrderItems> listOrderItems = null;

  public Order id(Long id) {
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

  public Order username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  */
  
  @Schema(name = "username", required = false)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Order address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  
  @Schema(name = "address", required = false)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Order phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
  */
  
  @Schema(name = "phone", required = false)
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Order listOrderItems(List<OrderItems> listOrderItems) {
    this.listOrderItems = listOrderItems;
    return this;
  }

  public Order addListOrderItemsItem(OrderItems listOrderItemsItem) {
    if (this.listOrderItems == null) {
      this.listOrderItems = new ArrayList<>();
    }
    this.listOrderItems.add(listOrderItemsItem);
    return this;
  }

  /**
   * Get listOrderItems
   * @return listOrderItems
  */
  @Valid 
  @Schema(name = "listOrderItems", required = false)
  public List<OrderItems> getListOrderItems() {
    return listOrderItems;
  }

  public void setListOrderItems(List<OrderItems> listOrderItems) {
    this.listOrderItems = listOrderItems;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.id, order.id) &&
        Objects.equals(this.username, order.username) &&
        Objects.equals(this.address, order.address) &&
        Objects.equals(this.phone, order.phone) &&
        Objects.equals(this.listOrderItems, order.listOrderItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, address, phone, listOrderItems);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    listOrderItems: ").append(toIndentedString(listOrderItems)).append("\n");
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

