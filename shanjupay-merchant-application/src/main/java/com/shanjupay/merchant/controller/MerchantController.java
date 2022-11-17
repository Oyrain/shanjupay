package com.shanjupay.merchant.controller;

import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.common.domain.CommonErrorCode;
import com.shanjupay.common.util.PhoneUtil;
import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.common.convert.MerchantDetailConvert;
import com.shanjupay.merchant.common.convert.MerchantRegisterConvert;
import com.shanjupay.merchant.common.util.SecurityUtil;
import com.shanjupay.merchant.service.FileService;
import com.shanjupay.merchant.service.SmsService;
import com.shanjupay.merchant.vo.MerchantDetailVO;
import com.shanjupay.merchant.vo.MerchantRegisterVO;
import com.shanjupay.transaction.api.dto.PayChannelDTO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.List;
import java.util.UUID;

@Api(value = "商户平台‐商户相关", tags = "商户平台‐商户相关", description = "商户平台‐商户相关")
@RestController
@Slf4j
public class MerchantController {

    @Reference
    private MerchantService merchantService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private FileService fileService;

    @ApiOperation("根据id查询商户")
    @GetMapping("merchants/{id}")
    public MerchantDTO quertMerchantById(@PathVariable("id") Long id) {
        MerchantDTO merchantDTO = merchantService.queryMerchantById(id);
        return merchantDTO;
    }

    @ApiOperation("获取手机验证码")
    @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String", paramType = "query")
    @GetMapping("/sms")
    public String getSMSCode(@RequestParam String phone) {
        log.info("向手机号:{}发送验证码", phone);
        String msg = smsService.sendMsg(phone);
        return msg;
    }

    @ApiOperation("注册商户")
    @ApiImplicitParam(name = "merchantRegister", value = "注册信息", required = true, dataType = "MerchantRegisterVO", paramType = "body")
    @PostMapping("/merchants/register")
    public MerchantRegisterVO registerMerchant(@RequestBody MerchantRegisterVO merchantRegisterVO) {

        // 1.校验
        if (merchantRegisterVO == null) {
            throw new BusinessException(CommonErrorCode.E_200202);
        }
        //手机号非空校验
        if (StringUtils.isBlank(merchantRegisterVO.getMobile())) {
            throw new BusinessException(CommonErrorCode.E_200230);
        }
        //校验手机号的合法性
        if (!PhoneUtil.isMatches(merchantRegisterVO.getMobile())) {
            throw new BusinessException(CommonErrorCode.E_200224);
        }
        //联系人非空校验
        if (StringUtils.isBlank(merchantRegisterVO.getUsername())) {
            throw new BusinessException(CommonErrorCode.E_200231);
        }
        //密码非空校验
        if (StringUtils.isBlank(merchantRegisterVO.getPassword())) {
            throw new BusinessException(CommonErrorCode.E_200232);
        }
        //验证码非空校验
        if (StringUtils.isBlank(merchantRegisterVO.getVerifiyCode()) ||
                StringUtils.isBlank(merchantRegisterVO.getVerifiykey())) {
            throw new BusinessException(CommonErrorCode.E_100103);
        }

        //校验验证码
        smsService.checkVerifiyCode(merchantRegisterVO.getVerifiykey(), merchantRegisterVO.getVerifiyCode());
        //注册商户
        MerchantDTO merchantDTO = MerchantRegisterConvert.INSTANCE.vo2dto(merchantRegisterVO);
        merchantService.createMerchant(merchantDTO);
        return merchantRegisterVO;
    }

    @ApiOperation("证件上传")
    @PostMapping("/upload")
    public String upload(@ApiParam(value = "上传的文件", required = true) @RequestParam("file") MultipartFile file) throws IOException, BatchUpdateException {
        //原始文件名称
        String originalFilename = file.getOriginalFilename();
        //文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")-1);
        //文件名称
        String fileName = UUID.randomUUID().toString()+suffix;
        //上传文件，返回文件下载ur
        String fileurl = fileService.upload(file.getBytes(), fileName);
        return fileurl;
    }

    @ApiOperation("商户资质申请")
    @ApiImplicitParams({@ApiImplicitParam(name = "merchantInfo", value = "商户认证资料", required = true,
                    dataType = "MerchantDetailVO", paramType = "body")
    })
    @PostMapping("/my/merchants/save")
    public void saveMerchant(@RequestBody MerchantDetailVO merchantDetailVO) {
        //解析token得到商户id
        Long merchantId = SecurityUtil.getMerchantId();
        MerchantDTO merchantDTO = MerchantDetailConvert.INSTANCE.vo2dto(merchantDetailVO);

        //资质申请
        merchantService.applyMerchant(merchantId,merchantDTO);
    }

}
