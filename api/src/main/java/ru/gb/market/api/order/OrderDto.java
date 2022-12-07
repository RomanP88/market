package ru.gb.market.api.order;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private String username;
    private BigDecimal totalPrice;
    private List<OrderItemDto> items;
    private String address;
    private String phone;
    private String statusCode;

    public OrderDto() {
    }

    public OrderDto(Long id, String username, BigDecimal totalPrice, List<OrderItemDto> items, String address, String phone, String statusCode) {
        this.id = id;
        this.username = username;
        this.totalPrice = totalPrice;
        this.items = items;
        this.address = address;
        this.phone = phone;
        this.statusCode = statusCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
