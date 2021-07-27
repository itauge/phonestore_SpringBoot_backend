package com.example.phone_store_demo_backend.repository;

import com.example.phone_store_demo_backend.entity.PhoneSpecs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneSpecsRepositoryTest {
    @Autowired
    PhoneSpecsRepository repository;

    @Test
    void findAll(){
        List<PhoneSpecs> all = repository.findAll();
        for (PhoneSpecs phoneSpecs : all) {
            System.out.println(phoneSpecs);
        }
    }


    @Test
    void findByPhoneId(){
        List<PhoneSpecs> allByPhoneId = repository.findAllByPhoneId(1);
        for (PhoneSpecs phoneSpecs : allByPhoneId) {
            System.out.println(phoneSpecs);
        }
    }
}