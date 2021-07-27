package com.example.phone_store_demo_backend.service.Impl;

import com.example.phone_store_demo_backend.entity.BuyerAddress;
import com.example.phone_store_demo_backend.form.AddressForm;
import com.example.phone_store_demo_backend.repository.BuyerAddressRepository;
import com.example.phone_store_demo_backend.service.AddressService;
import com.example.phone_store_demo_backend.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private BuyerAddressRepository buyerAddressRepository;

    @Override
    public List<AddressVO> findAll() {
        List<BuyerAddress> buyerAddressList = buyerAddressRepository.findAll();
        List<AddressVO> list = buyerAddressList.stream()
                .map(e -> new AddressVO(e.getAddressId(), e.getAreaCode(), e.getBuyerName(), e.getBuyerPhone(), e.getBuyerAddress())).collect(Collectors.toList());
        return list;
    }

    @Override
    public void saveOrUpdate(AddressForm addressForm) {
        BuyerAddress buyerAddress;
        if (addressForm.getId() == null){
            buyerAddress = new BuyerAddress();
        }else {
            buyerAddress = buyerAddressRepository.findById(addressForm.getId()).get();
        }
        buyerAddress.setBuyerName(addressForm.getName());
        buyerAddress.setBuyerPhone(addressForm.getTel());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(addressForm.getProvince())
                .append(addressForm.getCity())
                .append(addressForm.getCounty())
                .append(addressForm.getAddressDetail());
        buyerAddress.setBuyerAddress(stringBuffer.toString());
        buyerAddress.setAreaCode(addressForm.getAreaCode());

        buyerAddressRepository.save(buyerAddress);
    }
}
