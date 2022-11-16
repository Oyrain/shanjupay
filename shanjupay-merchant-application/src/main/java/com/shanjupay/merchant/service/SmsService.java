package com.shanjupay.merchant.service;

import com.shanjupay.common.domain.BusinessException;

public interface SmsService {

    //获取短信验证码
    String sendMsg(String phone);
    //校验验证码
    void checkVerifiyCode(String verifiyKey, String verifiyCode) throws BusinessException;
}
