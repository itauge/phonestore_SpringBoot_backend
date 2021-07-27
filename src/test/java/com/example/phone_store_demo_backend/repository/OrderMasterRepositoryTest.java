package com.example.phone_store_demo_backend.repository;

import com.example.phone_store_demo_backend.entity.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterRepositoryTest {

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Test
    void findall(){
        List<OrderMaster> all = orderMasterRepository.findAll();
        for (OrderMaster orderMaster : all) {
            System.out.println(orderMaster);
        }
    }

    @Test
    void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123");
        orderMaster.setBuyerName("test");
        orderMaster.setBuyerAddress("test");
        orderMaster.setBuyerPhone("13987876788");
        orderMaster.setOrderAmount(new BigDecimal(6400));
        orderMaster.setPayStatus(0);
        orderMaster.setPhoneIcon("../static");
        orderMaster.setPhoneId(1);
        orderMaster.setPhoneName("Honor 8A");
        orderMaster.setSpecsId(1);
        orderMaster.setSpecsName("32GB");
        orderMaster.setSpecsPrice(new BigDecimal(320000));
        orderMasterRepository.save(orderMaster);
    }

    @Test
    void findById(){
        OrderMaster orderMaster = orderMasterRepository.findById("123").get();
        System.out.println(orderMaster);
    }

    @Test
    void pay(){
        OrderMaster orderMaster = orderMasterRepository.findById("123").get();
        orderMaster.setPayStatus(1);
        orderMasterRepository.save(orderMaster);
    }

}