package com.example.phone_store_demo_backend.repository;

import com.example.phone_store_demo_backend.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
}
