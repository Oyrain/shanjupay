package com.shanjupay.merchant.controller;

import com.shanjupay.transaction.api.PayChannelService;
import com.shanjupay.transaction.api.dto.PlatformChannelDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "商户平台‐渠道和支付参数相关", tags = "商户平台‐渠道和支付参数", description = "商户平台-渠道和支付参数相关")
@Slf4j
@RestController
public class PlatformParamController {

    @Reference
    private PayChannelService payChannelService;

    @ApiOperation("获取平台服务类型")
    @GetMapping("/my/platform‐channels")
    public List<PlatformChannelDTO> queryPlatformChannel() {
        List<PlatformChannelDTO> platformChannelDTOS = payChannelService.queryPlatformChannel();
        return platformChannelDTOS;
    }

}
