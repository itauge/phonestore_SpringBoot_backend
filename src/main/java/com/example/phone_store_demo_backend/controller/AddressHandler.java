package com.example.phone_store_demo_backend.controller;

import com.example.phone_store_demo_backend.Exception.PhoneException;
import com.example.phone_store_demo_backend.form.AddressForm;
import com.example.phone_store_demo_backend.service.AddressService;
import com.example.phone_store_demo_backend.util.ResultVOUtil;
import com.example.phone_store_demo_backend.vo.AddressVO;
import com.example.phone_store_demo_backend.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressHandler {
    @Autowired
    AddressService addressService;

    @GetMapping("/list")
    public ResultVO list(){
        return ResultVOUtil.success(addressService.findAll());
    }

    @PostMapping("/create")
    public ResultVO create(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("[添加地址]參數錯誤,addressForm={}",addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressForm);
        return ResultVOUtil.success(null);
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody AddressForm addressForm,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("[修改地址]參數錯誤,addressForm={}", addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressForm);
        return ResultVOUtil.success(null);
    }



}
