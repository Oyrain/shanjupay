package com.shanjupay.merchant.api;

import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.merchant.api.dto.MerchantDTO;

public interface MerchantService {

    // 根据id查询商户
    MerchantDTO queryMerchantById(Long merchantId);
    // 商户注册
    MerchantDTO createMerchant(MerchantDTO merchantDTO) throws BusinessException;
    // 资质申请
    void applyMerchant(Long merchantId,MerchantDTO merchantDTO) throws BusinessException;
}
