package com.example.phone_store_demo_backend.vo;

import lombok.Data;

import java.util.List;

@Data
public class TreeVO {
    private String k = "規格";
    private List<PhoneSpecsVO> v;
    private String k_s = "s1";
}
