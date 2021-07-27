package com.example.phone_store_demo_backend.repository;

import com.example.phone_store_demo_backend.entity.BuyerAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.font.BidiUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyerAddressRepositoryTest {

    @Autowired
    BuyerAddressRepository repository;

    @Test
    void findAll(){
        List<BuyerAddress> all = repository.findAll();
        for (BuyerAddress buyerAddress : all) {
            System.out.println(buyerAddress);
        }
    }

    @Test
    void save(){
        BuyerAddress buyerAddress = new BuyerAddress();
        buyerAddress.setAreaCode("12345");
        buyerAddress.setBuyerAddress("你好");
        buyerAddress.setBuyerPhone("1398876772");
        buyerAddress.setBuyerName("哈哈");
        repository.save(buyerAddress);
    }

    @Test
    void update(){
        BuyerAddress buyerAddress = repository.findById(1).get();
        buyerAddress.setBuyerName("test3");
        repository.save(buyerAddress);
    }
}