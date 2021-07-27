package com.example.phone_store_demo_backend.repository;

import com.example.phone_store_demo_backend.entity.PhoneInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneInfoRepositoryTest {

    @Autowired
    PhoneInfoRepository repository;


    @Test
    void findAll(){
        List<PhoneInfo> all = repository.findAll();
        for (PhoneInfo phoneInfo : all) {
            System.out.println(phoneInfo);
        }
    }

    @Test
    void findAllByCategoryType(){
        List<PhoneInfo> allByCategoryType = repository.findAllByCategoryType(1);
        for (PhoneInfo phoneInfo : allByCategoryType) {
            System.out.println(phoneInfo);
        }
    }

}