package com.example.phone_store_demo_backend.repository;

import com.example.phone_store_demo_backend.entity.BuyerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerAddressRepository extends JpaRepository<BuyerAddress,Integer> {
}
