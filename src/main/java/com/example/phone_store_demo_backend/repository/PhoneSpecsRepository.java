package com.example.phone_store_demo_backend.repository;

import com.example.phone_store_demo_backend.entity.PhoneSpecs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneSpecsRepository extends JpaRepository<PhoneSpecs,Integer> {
    public List<PhoneSpecs> findAllByPhoneId(Integer phoneId);
}
