package com.shanjupay.merchant.common.convert;

import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.vo.MerchantDetailVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-16T00:01:46+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_321 (Oracle Corporation)"
)
public class MerchantDetailConvertImpl implements MerchantDetailConvert {

    @Override
    public MerchantDTO vo2dto(MerchantDetailVO vo) {
        if ( vo == null ) {
            return null;
        }

        MerchantDTO merchantDTO = new MerchantDTO();

        merchantDTO.setMerchantName( vo.getMerchantName() );
        merchantDTO.setMerchantNo( vo.getMerchantNo() );
        merchantDTO.setMerchantAddress( vo.getMerchantAddress() );
        merchantDTO.setMerchantType( vo.getMerchantType() );
        merchantDTO.setBusinessLicensesImg( vo.getBusinessLicensesImg() );
        merchantDTO.setIdCardFrontImg( vo.getIdCardFrontImg() );
        merchantDTO.setIdCardAfterImg( vo.getIdCardAfterImg() );
        merchantDTO.setUsername( vo.getUsername() );
        merchantDTO.setContactsAddress( vo.getContactsAddress() );

        return merchantDTO;
    }

    @Override
    public MerchantDetailVO dto2vo(MerchantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MerchantDetailVO merchantDetailVO = new MerchantDetailVO();

        merchantDetailVO.setMerchantName( dto.getMerchantName() );
        merchantDetailVO.setMerchantNo( dto.getMerchantNo() );
        merchantDetailVO.setMerchantAddress( dto.getMerchantAddress() );
        merchantDetailVO.setMerchantType( dto.getMerchantType() );
        merchantDetailVO.setBusinessLicensesImg( dto.getBusinessLicensesImg() );
        merchantDetailVO.setIdCardFrontImg( dto.getIdCardFrontImg() );
        merchantDetailVO.setIdCardAfterImg( dto.getIdCardAfterImg() );
        merchantDetailVO.setUsername( dto.getUsername() );
        merchantDetailVO.setContactsAddress( dto.getContactsAddress() );

        return merchantDetailVO;
    }
}
