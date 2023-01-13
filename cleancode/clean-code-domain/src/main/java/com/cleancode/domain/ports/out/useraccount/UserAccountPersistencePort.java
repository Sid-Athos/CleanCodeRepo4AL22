package com.cleancode.domain.ports.out.useraccount;

import com.cleancode.domain.pojo.user.BusinessUserClientInfo;

import java.util.Optional;

public interface UserAccountPersistencePort {
    Optional<BusinessUserClientInfo> findUserByUserName(String userName);

    Optional<BusinessUserClientInfo> saveUserInDb(BusinessUserClientInfo userToSave);
}
