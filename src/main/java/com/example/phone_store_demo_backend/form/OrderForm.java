package com.example.phone_store_demo_backend.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrderForm {
    @NotEmpty(message = "name can not be empty")
    private String name;
    @NotEmpty(message = "tel can not be empty")
    private String tel;
    @NotEmpty(message = "address can not be empty")
    private String address;
    @NotNull(message = "specs can not be null")
    private Integer specsId;
    @NotNull(message = "quantity can not be null")
    private Integer quantity;
}
