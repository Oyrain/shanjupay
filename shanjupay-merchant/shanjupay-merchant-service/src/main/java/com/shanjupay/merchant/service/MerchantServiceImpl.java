package com.shanjupay.merchant.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.common.domain.CommonErrorCode;
import com.shanjupay.common.util.PhoneUtil;
import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.convert.MerchantConvert;
import com.shanjupay.merchant.entity.Merchant;
import com.shanjupay.merchant.mapper.MerchantMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    /**
     * 根据id查询商户
     * @param merchantId
     * @return
     */
    @Override
    public MerchantDTO queryMerchantById(Long merchantId) {
        Merchant merchant = merchantMapper.selectById(merchantId);
        MerchantDTO merchantDTO = MerchantConvert.INSTANCE.entity2dto(merchant);
        //...
        return merchantDTO;
    }

    /**
     * 注册商户
     * @param merchantDTO
     * @return
     */
    @Override
    public MerchantDTO createMerchant(MerchantDTO merchantDTO) {

        //1.校验
        if (merchantDTO == null) {
            throw new BusinessException(CommonErrorCode.E_200202);
        }
        //手机号非空校验
        if (StringUtils.isBlank(merchantDTO.getMobile())) {
            throw new BusinessException(CommonErrorCode.E_200230);
        }
        //校验手机号的合法性
        if (!PhoneUtil.isMatches(merchantDTO.getMobile())) {
            throw new BusinessException(CommonErrorCode.E_200224);
        }
        //联系人非空校验
        if (StringUtils.isBlank(merchantDTO.getUsername())) {
            throw new BusinessException(CommonErrorCode.E_200231);
        }
        //密码非空校验
        if (StringUtils.isBlank(merchantDTO.getPassword())) {
            throw new BusinessException(CommonErrorCode.E_200232);
        }
        //校验商户手机号的唯一性,根据商户的手机号查询商户表，如果存在记录则说明已有相同的手机号重复
        LambdaQueryWrapper<Merchant> lambdaQryWrapper = new LambdaQueryWrapper<Merchant>()
                .eq(Merchant::getMobile,merchantDTO.getMobile());
        Integer count = merchantMapper.selectCount(lambdaQryWrapper);
        if(count>0){
            throw new BusinessException(CommonErrorCode.E_200203);
        }

        Merchant merchant = MerchantConvert.INSTANCE.dto2entity(merchantDTO);
        merchant.setAuditStatus("0");//设置审核状态0-未申请,1-已申请待审核,2-审核通过,3-审核拒绝
        //保存商户
        merchantMapper.insert(merchant);
        //将新增商户id返回
        merchantDTO.setId(merchant.getId());
        return merchantDTO;
    }

    /**
     * 资质申请
     * @param merchantId
     * @param merchantDTO
     * @throws BusinessException
     */
    @Override
    public void applyMerchant(Long merchantId, MerchantDTO merchantDTO) throws BusinessException {
        // 接收资质申请信息，更新到商户表
        if (merchantDTO == null || merchantId == null) {
            throw new BusinessException(CommonErrorCode.E_200202);
        }

        //根据id查询商户
        Merchant merchant = merchantMapper.selectById(merchantId);
        if (merchant == null) {
            throw new BusinessException(CommonErrorCode.E_200207);
        }

        Merchant merchantNew = MerchantConvert.INSTANCE.dto2entity(merchantDTO);
        merchantNew.setAuditStatus("1");// 已申请待审核
        merchantNew.setTenantId(merchant.getTenantId());
        merchantNew.setId(merchant.getId());

        merchantMapper.updateById(merchantNew);
    }

}
