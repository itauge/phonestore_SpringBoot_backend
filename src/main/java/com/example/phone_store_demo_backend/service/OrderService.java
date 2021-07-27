package com.example.phone_store_demo_backend.service;

import com.example.phone_store_demo_backend.dto.OrderDTO;
import com.example.phone_store_demo_backend.vo.OrderDetailVO;

public interface OrderService {
    public OrderDTO create(OrderDTO orderDTO);
    public OrderDetailVO findOrderDetailByOrderId(String orderId);
    public String pay(String orderId);
}
