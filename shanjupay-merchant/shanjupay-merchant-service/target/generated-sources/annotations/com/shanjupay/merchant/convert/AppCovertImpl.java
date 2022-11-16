package com.shanjupay.merchant.convert;

import com.shanjupay.merchant.api.dto.AppDTO;
import com.shanjupay.merchant.entity.App;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-16T00:01:44+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_321 (Oracle Corporation)"
)
public class AppCovertImpl implements AppCovert {

    @Override
    public AppDTO entity2dto(App entity) {
        if ( entity == null ) {
            return null;
        }

        AppDTO appDTO = new AppDTO();

        appDTO.setId( entity.getId() );
        appDTO.setAppId( entity.getAppId() );
        appDTO.setAppName( entity.getAppName() );
        appDTO.setMerchantId( entity.getMerchantId() );
        appDTO.setPublicKey( entity.getPublicKey() );
        appDTO.setNotifyUrl( entity.getNotifyUrl() );

        return appDTO;
    }

    @Override
    public App dto2entity(AppDTO dto) {
        if ( dto == null ) {
            return null;
        }

        App app = new App();

        app.setId( dto.getId() );
        app.setAppId( dto.getAppId() );
        app.setAppName( dto.getAppName() );
        app.setMerchantId( dto.getMerchantId() );
        app.setPublicKey( dto.getPublicKey() );
        app.setNotifyUrl( dto.getNotifyUrl() );

        return app;
    }

    @Override
    public List<AppDTO> listentity2dto(List<App> app) {
        if ( app == null ) {
            return null;
        }

        List<AppDTO> list = new ArrayList<AppDTO>( app.size() );
        for ( App app1 : app ) {
            list.add( entity2dto( app1 ) );
        }

        return list;
    }
}