package com.example.phone_store_demo_backend.vo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.util.List;

@Data
public class SkuVO {
    private List<TreeVO> tree;
    private List<PhoneSpecsCaseVO> list;
    private String price;
    private Integer stock_num;
    private Boolean none_sku = false;
    private Boolean hide_stock = false;
}
