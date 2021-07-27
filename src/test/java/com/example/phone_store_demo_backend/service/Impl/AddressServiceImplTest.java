package com.example.phone_store_demo_backend.service.Impl;

import com.example.phone_store_demo_backend.form.AddressForm;
import com.example.phone_store_demo_backend.service.AddressService;
import com.example.phone_store_demo_backend.vo.AddressVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceImplTest {

    @Autowired
    AddressService addressService;
    @Test
    void findAll() {
        List<AddressVO> all = addressService.findAll();
        int i = 0;
    }

    @Test
    void  saveOrUpdate(){
        AddressForm addressForm = new AddressForm();
        addressForm.setId(36);
        addressForm.setName("test");
        addressForm.setTel("139876");
        addressForm.setProvince("北京市");
        addressForm.setCity("北京市");
        addressForm.setCounty("東城區");
        addressForm.setAreaCode("110101");
        addressForm.setAddressDetail("168號306");
        addressService.saveOrUpdate(addressForm);
    }
}