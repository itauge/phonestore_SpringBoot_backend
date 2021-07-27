package com.example.phone_store_demo_backend.vo;

import lombok.Data;

import java.util.List;

@Data
public class DataVO {
    private List<PhoneCategoryVO> categories;
    private List<PhoneInfoVO> phones;
}
