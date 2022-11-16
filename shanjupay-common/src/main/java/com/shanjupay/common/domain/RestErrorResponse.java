package com.shanjupay.common.domain;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "RestErrorResponse", description = "错误响应参数包装")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestErrorResponse {

    private String errCode;

    private String errMessage;


}
