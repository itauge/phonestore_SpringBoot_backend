package com.example.phone_store_demo_backend.service;

import com.example.phone_store_demo_backend.vo.DataVO;
import com.example.phone_store_demo_backend.vo.PhoneInfoVO;
import com.example.phone_store_demo_backend.vo.SpecsPackageVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PhoneService {
    public DataVO findDataVO();
    public List<PhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryType);
    public SpecsPackageVO findSpecsByPhoneId(Integer phoneId);
    public void subStock(Integer specsId,Integer quantity);
}
