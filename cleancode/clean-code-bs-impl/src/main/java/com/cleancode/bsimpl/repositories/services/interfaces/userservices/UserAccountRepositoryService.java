package com.cleancode.bsimpl.repositories.services.interfaces.userservices;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;

import java.util.Optional;

public interface UserAccountRepositoryService {
    Optional<BusinessUserClientInfo> findUserByUserName(String userName);

    Optional<BusinessUserClientInfo> saveUserInDb(BusinessUserClientInfo userToSave);
}
