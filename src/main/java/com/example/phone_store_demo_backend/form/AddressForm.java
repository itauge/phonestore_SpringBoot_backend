package com.example.phone_store_demo_backend.form;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddressForm {
    private Integer id;
    @NotEmpty(message = "name can't empty")
    private String name;
    @NotEmpty(message = "telephone can't empty")
    private String tel;
    @NotEmpty(message = "province can't empty")
    private String province;
    @NotEmpty(message = "city can't empty")
    private String city;
    @NotEmpty(message = "county can't empty")
    private String county;
    @NotEmpty(message = "area code can't empty")
    private String areaCode;
    @NotEmpty(message = "address detail can't empty")
    private String addressDetail;
}
