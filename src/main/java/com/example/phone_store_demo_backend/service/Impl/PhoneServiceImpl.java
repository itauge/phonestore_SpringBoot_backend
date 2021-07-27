package com.example.phone_store_demo_backend.service.Impl;

import com.example.phone_store_demo_backend.Exception.PhoneException;
import com.example.phone_store_demo_backend.entity.PhoneCategory;
import com.example.phone_store_demo_backend.entity.PhoneInfo;
import com.example.phone_store_demo_backend.entity.PhoneSpecs;
import com.example.phone_store_demo_backend.enums.ResultEnum;
import com.example.phone_store_demo_backend.repository.PhoneCategoryRepository;
import com.example.phone_store_demo_backend.repository.PhoneInfoRepository;
import com.example.phone_store_demo_backend.repository.PhoneSpecsRepository;
import com.example.phone_store_demo_backend.service.PhoneService;
import com.example.phone_store_demo_backend.util.PhoneUtil;
import com.example.phone_store_demo_backend.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneCategoryRepository phoneCategoryRepository;

    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;


    @Override
    public DataVO findDataVO() {
        DataVO dataVO = new DataVO();
        //類型
        List<PhoneCategory> phoneCategoryList = phoneCategoryRepository.findAll();
        //stream寫法
        List<PhoneCategoryVO> phoneCategoryVOList = phoneCategoryList.stream()
                .map(e -> new PhoneCategoryVO(e.getCategoryName(), e.getCategoryType())).collect(Collectors.toList());
        dataVO.setCategories(phoneCategoryVOList);

        //手機
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findAllByCategoryType(phoneCategoryList.get(0).getCategoryType());
        List<PhoneInfoVO> phoneInfoVOList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice() + ".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());

        dataVO.setPhones(phoneInfoVOList);

        return dataVO;
    }

    @Override
    public List<PhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryType) {
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findAllByCategoryType(categoryType);
        List<PhoneInfoVO> phoneInfoVOList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice()+".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon())).collect(Collectors.toList());
        return phoneInfoVOList;
    }

    @Override
    public SpecsPackageVO findSpecsByPhoneId(Integer phoneId) {
        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneId).get();
        List<PhoneSpecs> phoneSpecsList = phoneSpecsRepository.findAllByPhoneId(phoneId);

        //tree
        List<PhoneSpecsVO> phoneSpecsVOList = new ArrayList<>();
        List<PhoneSpecsCaseVO> phoneSpecsCaseVOList = new ArrayList<>();
        PhoneSpecsVO phoneSpecsVO;
        PhoneSpecsCaseVO phoneSpecsCaseVO;
        //用常規方式不用stream方式
        for (PhoneSpecs phoneSpecs : phoneSpecsList) {
            phoneSpecsVO = new PhoneSpecsVO();
            phoneSpecsCaseVO = new PhoneSpecsCaseVO();
            BeanUtils.copyProperties(phoneSpecs,phoneSpecsVO);
            BeanUtils.copyProperties(phoneSpecs,phoneSpecsCaseVO);
            phoneSpecsVOList.add(phoneSpecsVO);
            phoneSpecsCaseVOList.add(phoneSpecsCaseVO);
        }

        TreeVO treeVO = new TreeVO();
        treeVO.setV(phoneSpecsVOList);

        List<TreeVO> treeVOList = new ArrayList<>();
        treeVOList.add(treeVO);

        SkuVO skuVO = new SkuVO();
        Integer price = phoneInfo.getPhonePrice().intValue();
        skuVO.setPrice(price+".00");
        skuVO.setStock_num(phoneInfo.getPhoneStock());
        skuVO.setTree(treeVOList);
        skuVO.setList(phoneSpecsCaseVOList);

        SpecsPackageVO specsPackageVO = new SpecsPackageVO();
        specsPackageVO.setSku(skuVO);

        Map<String,String> goods = new HashMap<>();
        goods.put("picture",phoneInfo.getPhoneIcon());
        specsPackageVO.setGoods(goods);

        return specsPackageVO;
    }

    @Override
    public void subStock(Integer specsId, Integer quantity) {
        PhoneSpecs phoneSpecs = phoneSpecsRepository.findById(specsId).get();
        //規格減庫存之後,手機對應型號總庫存也要減少
        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();
        Integer result = phoneSpecs.getSpecsStock() - quantity;
        if (result < 0 ){
            log.error("減庫存，庫存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }

        phoneSpecs.setSpecsStock(result);
        phoneSpecsRepository.save(phoneSpecs);

        result = phoneInfo.getPhoneStock() - quantity;
        if (result < 0 ){
            log.error("減庫存，庫存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }
        phoneInfo.setPhoneStock(result);
        phoneInfoRepository.save(phoneInfo);
    }
}
