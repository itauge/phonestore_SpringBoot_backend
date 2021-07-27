package com.example.phone_store_demo_backend.service;

import com.example.phone_store_demo_backend.form.AddressForm;
import com.example.phone_store_demo_backend.vo.AddressVO;

import java.util.List;

public interface AddressService {
    public List<AddressVO> findAll();
    public void saveOrUpdate(AddressForm addressForm);

}
