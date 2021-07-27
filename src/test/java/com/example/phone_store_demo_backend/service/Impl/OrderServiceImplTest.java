package com.example.phone_store_demo_backend.service.Impl;

import com.example.phone_store_demo_backend.dto.OrderDTO;
import com.example.phone_store_demo_backend.service.OrderService;
import com.example.phone_store_demo_backend.vo.OrderDetailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    void create(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("test123");
        orderDTO.setBuyerPhone("139872398");
        orderDTO.setBuyerAddress("test");
        orderDTO.setSpecsId(1);
        orderDTO.setPhoneQuantity(1);

        OrderDTO result = orderService.create(orderDTO);
        System.out.println(result);
    }

    @Test
    void findOrderDetailByOrderId(){
        OrderDetailVO orderDetailVO = orderService.findOrderDetailByOrderId("123");
        int i = 0;
    }

    @Test
    void pay(){
        orderService.pay("123");
    }

}