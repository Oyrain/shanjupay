package com.shanjupay.merchant.controller;

import com.shanjupay.merchant.api.AppService;
import com.shanjupay.merchant.api.dto.AppDTO;
import com.shanjupay.merchant.common.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "商户平台‐应用管理", tags = "商户平台‐应用相关", description = "商户平台‐应用相关")
@RestController
public class AppController {

    @Reference
    private AppService appService;

    @ApiOperation("商户创建应用")
    @ApiImplicitParams({@ApiImplicitParam(name = "app", value = "应用信息", required = true, dataType = "AppDTO", paramType = "body")})
    @PostMapping("/my/apps")
    public AppDTO createApp(@RequestBody AppDTO app) {
        Long merchantId = SecurityUtil.getMerchantId();
        return appService.createApp(merchantId,app);
    }

    @ApiOperation("查询商户下的应用列表")
    @GetMapping("/my/apps")
    public List<AppDTO> queryApps() {
        Long merchantId = SecurityUtil.getMerchantId();
        List<AppDTO> appDTOS = appService.queryAppByMerchant(merchantId);
        return appDTOS;
    }

    @ApiOperation("根据appid获取应用的详细信息")
    @GetMapping("/my/apps/{id}")
    public AppDTO getApp(@PathVariable String id) {
        AppDTO app = appService.getAppById(id);
        return app;
    }

}
