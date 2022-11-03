package com.cleancode.bsimpl.mappers.users;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserClientInfoMapper {
    UserClientInfoMapper INSTANCE = Mappers.getMapper(UserClientInfoMapper.class);
    @Mappings({
            @Mapping(source = "clientReference", target = "businessReference")
    })
    BusinessUserClientInfo fromApiToBs(UserClientInfo userClientInfo);

    @Mappings({
            @Mapping(source = "businessReference", target = "clientReference")
    })
    UserClientInfo fromBsToApi(BusinessUserClientInfo userClientInfo);
}
