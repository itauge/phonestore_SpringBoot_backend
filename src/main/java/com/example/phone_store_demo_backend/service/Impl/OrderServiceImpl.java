package com.example.phone_store_demo_backend.service.Impl;

import com.example.phone_store_demo_backend.Exception.PhoneException;
import com.example.phone_store_demo_backend.dto.OrderDTO;
import com.example.phone_store_demo_backend.entity.OrderMaster;
import com.example.phone_store_demo_backend.entity.PhoneInfo;
import com.example.phone_store_demo_backend.entity.PhoneSpecs;
import com.example.phone_store_demo_backend.enums.PayStatusEnum;
import com.example.phone_store_demo_backend.enums.ResultEnum;
import com.example.phone_store_demo_backend.repository.OrderMasterRepository;
import com.example.phone_store_demo_backend.repository.PhoneInfoRepository;
import com.example.phone_store_demo_backend.repository.PhoneSpecsRepository;
import com.example.phone_store_demo_backend.service.OrderService;
import com.example.phone_store_demo_backend.service.PhoneService;
import com.example.phone_store_demo_backend.util.KeyUtil;
import com.example.phone_store_demo_backend.vo.OrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;

    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private PhoneService phoneService;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        PhoneSpecs phoneSpecs = phoneSpecsRepository.findById(orderDTO.getSpecsId()).get();

        if (phoneSpecs == null){
            log.error("[創建訂單]規格為空,phoneSpecs={}",phoneSpecs);
            throw new PhoneException(ResultEnum.SPECS_NOT_EXIST);
        }

        BeanUtils.copyProperties(phoneSpecs,orderMaster);
        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();
        if (phoneInfo == null){
            log.error("[創建訂單]手機為空,phoneInfo={}",phoneInfo);
            throw new PhoneException(ResultEnum.PHONE_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneInfo,orderMaster);

        //計算總價
        BigDecimal orderAmount = new BigDecimal(0);
        orderAmount = phoneSpecs.getSpecsPrice().divide(new BigDecimal(100))
                .multiply(new BigDecimal(orderDTO.getPhoneQuantity()))
                .add(orderAmount)
                .add(new BigDecimal(10));//加運費
        orderMaster.setOrderAmount(orderAmount);

        //orderId
        orderMaster.setOrderId(KeyUtil.createUniqueKey());
        orderDTO.setOrderId(orderMaster.getOrderId());

        //pay status
        orderMaster.setPayStatus(PayStatusEnum.UNPAID.getCode());

        orderMasterRepository.save(orderMaster);

        //改庫存
        phoneService.subStock(orderDTO.getSpecsId(),orderDTO.getPhoneQuantity());

        return orderDTO;

    }

    @Override
    public OrderDetailVO findOrderDetailByOrderId(String orderId) {
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();

        if (orderMaster == null){
            log.error("[查詢訂單]訂單為空,orderMaster={}",orderMaster);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }

        BeanUtils.copyProperties(orderMaster,orderDetailVO);

        //orderMaster的specsPrice是big decimal所以要自己轉換
        orderDetailVO.setSpecsPrice(orderMaster.getSpecsPrice().divide(new BigDecimal(100))+".00");


        return orderDetailVO;
    }

    @Override
    public String pay(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null){
            log.error("訂單為空,orderMaster={}",orderMaster);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (orderMaster.getPayStatus().equals(PayStatusEnum.UNPAID.getCode())){
            orderMaster.setPayStatus(PayStatusEnum.PAID.getCode());
            orderMasterRepository.save(orderMaster);
        }else{
            log.error("[支付訂單]訂單已支付,orderMaster={}",orderMaster);
        }
        return orderId;
    }
}
