package com.shanjupay.merchant.service.impl;

import com.alibaba.fastjson.JSON;
import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.common.domain.CommonErrorCode;
import com.shanjupay.merchant.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    @Value("${sms.url}")
    private String smsUrl;

    @Value("${sms.effectiveTime}")
    private String effectiveTime;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取短信验证码
     * @param phone
     * @return
     */
    @Override
    public String sendMsg(String phone) {
        String url = smsUrl + "/generate?name=sms&effectiveTime=" + effectiveTime;
        log.info("调用短信微服务发送验证码：url:{}", url);

        Map<String,Object> body = new HashMap<>();
        body.put("mobile",phone);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity<>(body,httpHeaders);

        Map responseMap = null;
        try {
            ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
            log.info("调用短信微服务发送验证码: 返回值:{}", JSON.toJSONString(exchange));
            responseMap = exchange.getBody();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw new RuntimeException("发送验证码出错");
        }

        if (responseMap == null || responseMap.get("result") == null) {
            throw new RuntimeException("发送验证码出错");
        }

        Map resultMap = (Map) responseMap.get("result");

        return resultMap.get("key").toString();
    }

    /**
     * 校验验证码，抛出异常则校验无效
     * 校验验证码
     * @param verifiyKey 验证码key
     * @param verifiyCode 验证码
     */
    @Override
    public void checkVerifiyCode(String verifiyKey, String verifiyCode) {
        String url = smsUrl + "/verify?name=sms&verificationCode=" + verifiyCode + "&verificationKey=" + verifiyKey;
        //请求校验验证码
        Map responseMap = null;
        try {
            ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, HttpEntity.EMPTY, Map.class);
            responseMap = exchange.getBody();
            log.info("校验验证码，响应内容：{}", JSON.toJSONString(responseMap));
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage(),e);
            throw new BusinessException(CommonErrorCode.E_100102);
            //throw new RuntimeException("验证码错误");
        }

        if (responseMap == null || !(Boolean)responseMap.get("result")) {
            throw new BusinessException(CommonErrorCode.E_100102);
            //throw new RuntimeException("验证码错误");
        }

    }

}
