package com.example.phone_store_demo_backend.Exception;

import com.example.phone_store_demo_backend.enums.ResultEnum;

public class PhoneException extends RuntimeException{
    public PhoneException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }

    public PhoneException(String error){
        super(error);
    }
}
