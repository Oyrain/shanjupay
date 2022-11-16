package com.shanjupay.merchant.api;

import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.merchant.api.dto.AppDTO;

import java.util.List;

public interface AppService {

    //商户下创建应用
    AppDTO createApp(Long merchantId, AppDTO app) throws BusinessException;
    //查询商户下的应用列表
    List<AppDTO> queryAppByMerchant(Long merchantId) throws BusinessException;
    //根据业务id查询商户
    AppDTO getAppById(String id) throws BusinessException;
}
