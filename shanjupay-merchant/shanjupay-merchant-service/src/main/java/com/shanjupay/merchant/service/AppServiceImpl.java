package com.shanjupay.merchant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.common.domain.CommonErrorCode;
import com.shanjupay.common.util.RandomUuidUtil;
import com.shanjupay.merchant.api.AppService;
import com.shanjupay.merchant.api.dto.AppDTO;
import com.shanjupay.merchant.convert.AppCovert;
import com.shanjupay.merchant.entity.App;
import com.shanjupay.merchant.entity.Merchant;
import com.shanjupay.merchant.mapper.AppMapper;
import com.shanjupay.merchant.mapper.MerchantMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public AppDTO createApp(Long merchantId, AppDTO app) throws BusinessException {
        //校验商户是否通过资质审核
        Merchant merchant = merchantMapper.selectById(merchantId);
        if (merchant == null) {
            throw new BusinessException(CommonErrorCode.E_200231);
        }
        if (!"2".equals(merchant.getAuditStatus())) {
            throw new BusinessException(CommonErrorCode.E_200236);
        }

        if (isExistAppName(app.getAppName())) {
            throw new BusinessException(CommonErrorCode.E_200225);
        }
        //保存用户信息
        app.setAppId(RandomUuidUtil.getUUID());
        app.setMerchantId(merchant.getId());
        App entity = AppCovert.INSTANCE.dto2entity(app);
        appMapper.insert(entity);
        return AppCovert.INSTANCE.entity2dto(entity);
    }

    @Override
    public List<AppDTO> queryAppByMerchant(Long merchantId) throws BusinessException {
        List<App> apps = appMapper.selectList(new QueryWrapper<App>().lambda().eq(App::getMerchantId, merchantId));
        List<AppDTO> appDTOS = AppCovert.INSTANCE.listentity2dto(apps);
        return appDTOS;
    }

    @Override
    public AppDTO getAppById(String id) throws BusinessException {
        App app = appMapper.selectOne(new QueryWrapper<App>().lambda().eq(App::getAppId, id));
        return AppCovert.INSTANCE.entity2dto(app);
    }

    /**
     * 检验用户名是否已被使用
     * @param appName
     * @return
     */
    public Boolean isExistAppName(String appName) {
        Integer count = appMapper.selectCount(new QueryWrapper<App>().lambda().eq(App::getAppName, appName));
        return count.intValue() > 0;
    }

}
