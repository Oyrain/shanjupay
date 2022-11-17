package com.shanjupay.merchant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shanjupay.common.util.EncryptUtil;
import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.api.dto.MerchantDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Administrator
 * @version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenTemp {

    @Reference
    private MerchantService merchantService;
    String s = "{\"appID\": \"wxd2bf2dba2e86a8c7\",\"appSecret\": \"cec1a9185ad435abe1bced4b93f7ef2e\",\"key\": \"95fe355daca50f1ae82f0865c2ce87c8\",\"mchID\": \"1502570431\",\"payKey\": \"95fe355daca50f1ae82f0865c2ce87c8\"}";

    @Test
    public void createTestToken() {
        Long merchantId = 1591093993350815745L;//填写用于测试的商户id
        MerchantDTO merchantDTO = merchantService.queryMerchantById(merchantId);
        JSONObject token = new JSONObject();
        token.put("mobile", merchantDTO.getMobile());
        token.put("user_name", merchantDTO.getUsername());
        token.put("merchantId", merchantId);

        String jwt_token = "Bearer " + EncryptUtil.encodeBase64(JSON.toJSONString(token).getBytes());
        System.out.println(jwt_token);
    }
}
