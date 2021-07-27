package com.example.phone_store_demo_backend.vo;

import lombok.Data;

import java.util.Map;

@Data
public class SpecsPackageVO {
    private Map<String,String> goods;
    private SkuVO sku;
}
