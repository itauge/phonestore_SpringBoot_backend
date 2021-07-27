package com.example.phone_store_demo_backend.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PHONE_STOCK_ERROR(0,"手機庫存不足"),
    ORDER_NOT_EXIST(1,"訂單不存在"),
    SPECS_NOT_EXIST(2,"規格不存在"),
    PHONE_NOT_EXIST(3,"手機不存在");


    private Integer code;
    private String msg;


    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
