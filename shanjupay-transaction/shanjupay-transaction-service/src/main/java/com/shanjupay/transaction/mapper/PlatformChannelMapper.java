package com.shanjupay.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanjupay.transaction.api.dto.PayChannelDTO;
import com.shanjupay.transaction.entity.PlatformChannel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2019-11-15
 */
@Repository
public interface PlatformChannelMapper extends BaseMapper<PlatformChannel> {
    @Select("SELECT " +
            "pay.* " +
            "FROM " +
            "pay_channel pay,platform_pay_channel ppc,platform_channel pc " +
            "WHERE " +
            "pay.CHANNEL_CODE = ppc.PAY_CHANNEL " +
            "AND pc.CHANNEL_CODE = ppc.PLATFORM_CHANNEL " +
            "AND pc.CHANNEL_CODE = #{platformChannelCode}")
    public List<PayChannelDTO> selectPayChannelByPlatformChannel(String platformChannelCode) ;

}
