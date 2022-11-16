package com.shanjupay.merchant.convert;

import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MerchantConvert {

    MerchantConvert INSTANCE = Mappers.getMapper(MerchantConvert.class);

    MerchantDTO entity2dto(Merchant entity);

    Merchant dto2entity(MerchantDTO dto);

    //list之间的转换
    List<MerchantDTO> listentity2dto(List<Merchant> list);

}
