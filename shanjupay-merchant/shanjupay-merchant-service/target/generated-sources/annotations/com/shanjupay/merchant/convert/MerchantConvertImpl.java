package com.shanjupay.merchant.convert;

import com.shanjupay.merchant.api.dto.MerchantDTO;
import com.shanjupay.merchant.entity.Merchant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-16T00:01:44+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_321 (Oracle Corporation)"
)
public class MerchantConvertImpl implements MerchantConvert {

    @Override
    public MerchantDTO entity2dto(Merchant entity) {
        if ( entity == null ) {
            return null;
        }

        MerchantDTO merchantDTO = new MerchantDTO();

        merchantDTO.setId( entity.getId() );
        merchantDTO.setMerchantName( entity.getMerchantName() );
        merchantDTO.setMerchantNo( entity.getMerchantNo() );
        merchantDTO.setMerchantAddress( entity.getMerchantAddress() );
        merchantDTO.setMerchantType( entity.getMerchantType() );
        merchantDTO.setBusinessLicensesImg( entity.getBusinessLicensesImg() );
        merchantDTO.setIdCardFrontImg( entity.getIdCardFrontImg() );
        merchantDTO.setIdCardAfterImg( entity.getIdCardAfterImg() );
        merchantDTO.setUsername( entity.getUsername() );
        merchantDTO.setMobile( entity.getMobile() );
        merchantDTO.setContactsAddress( entity.getContactsAddress() );
        merchantDTO.setAuditStatus( entity.getAuditStatus() );
        merchantDTO.setTenantId( entity.getTenantId() );

        return merchantDTO;
    }

    @Override
    public Merchant dto2entity(MerchantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Merchant merchant = new Merchant();

        merchant.setId( dto.getId() );
        merchant.setMerchantName( dto.getMerchantName() );
        merchant.setMerchantNo( dto.getMerchantNo() );
        merchant.setMerchantAddress( dto.getMerchantAddress() );
        merchant.setMerchantType( dto.getMerchantType() );
        merchant.setBusinessLicensesImg( dto.getBusinessLicensesImg() );
        merchant.setIdCardFrontImg( dto.getIdCardFrontImg() );
        merchant.setIdCardAfterImg( dto.getIdCardAfterImg() );
        merchant.setUsername( dto.getUsername() );
        merchant.setMobile( dto.getMobile() );
        merchant.setContactsAddress( dto.getContactsAddress() );
        merchant.setAuditStatus( dto.getAuditStatus() );
        merchant.setTenantId( dto.getTenantId() );

        return merchant;
    }

    @Override
    public List<MerchantDTO> listentity2dto(List<Merchant> list) {
        if ( list == null ) {
            return null;
        }

        List<MerchantDTO> list1 = new ArrayList<MerchantDTO>( list.size() );
        for ( Merchant merchant : list ) {
            list1.add( entity2dto( merchant ) );
        }

        return list1;
    }
}
