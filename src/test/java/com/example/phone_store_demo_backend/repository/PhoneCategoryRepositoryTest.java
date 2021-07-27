package com.example.phone_store_demo_backend.repository;

import com.example.phone_store_demo_backend.entity.PhoneCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class PhoneCategoryRepositoryTest {
    @Autowired
    PhoneCategoryRepository phoneCategoryRepository;

    @Test
    void findall(){
        List<PhoneCategory> list = phoneCategoryRepository.findAll();
        for (PhoneCategory phoneCategory : list) {
            System.out.println(phoneCategory);
        }
    }

    @Test
    void findByCategoryType(){
        PhoneCategory phoneCategory = phoneCategoryRepository.findByCategoryType(1);
        System.out.println(phoneCategory);
    }

}