package com.shanjupay.merchant;

import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.convert.MerchantConvert;
import com.shanjupay.merchant.entity.Merchant;

public class MerchantConvertTest {

    public static void main(String[] args) {
        //dto转entity
        MerchantDTO merchantDTO = new MerchantDTO();
        merchantDTO.setUsername("测试");
        Merchant entity = MerchantConvert.INSTANCE.dto2entity(merchantDTO);
        System.out.println(entity.getUsername());
        //entity转dto
        entity.setMobile("123444554");
        MerchantDTO merchantDTO1 = MerchantConvert.INSTANCE.entity2dto(entity);
        System.out.println(merchantDTO1.getMobile());

    }

}
