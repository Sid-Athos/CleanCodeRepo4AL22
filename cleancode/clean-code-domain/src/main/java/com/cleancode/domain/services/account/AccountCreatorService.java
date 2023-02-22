package com.cleancode.domain.services.account;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.core.lib.userserviceutils.UserAccountOperationUtils;
import com.cleancode.domain.pojo.user.AccountCreationCommand;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.in.user.AccountCreator;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.jnape.palatable.lambda.adt.Maybe;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountCreatorService implements AccountCreator {
    private static final Logger LOGGER = Logger.getLogger(AccountCreatorService.class.getName());
    private final UserAccountPersistencePort userAccountPersistencePort;

    public AccountCreatorService(UserAccountPersistencePort userAccountPersistencePort){
        this.userAccountPersistencePort = userAccountPersistencePort;
    }

    /**
     * @param userFromApi a user from api
     * @return something
     */
    @Override
    public BusinessUserClientInfo saveUserAccount(AccountCreationCommand userFromApi) throws CleanCodeException {
        userAccountPersistencePort.findUserByUserName(userFromApi.getUserName())
                .fmap(user -> {
                    throw new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL);
                        });
        BusinessUserClientInfo newAccount= new BusinessUserClientInfo(
                userFromApi.getUserName(),
                null,
                null,
                null,
                null,
                4L);
        UserAccountOperationUtils.handleBusinessUserReferenceCreation(newAccount);
        UserAccountOperationUtils.handleInitBusinessUserCardCollection(userFromApi.getCollectionName(), newAccount);
            try {
                Maybe<BusinessUserClientInfo> returnedBusinessUserClientInfo = userAccountPersistencePort.saveUserInDb(newAccount);
                LOGGER.log(Level.INFO, "UserFromApi User : " + userFromApi + " Returned user : " + returnedBusinessUserClientInfo);
                return  returnedBusinessUserClientInfo.orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL));
            } catch (Exception cardCollectionCreationException){
                UserAccountOperationUtils.revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(newAccount);
                handleDBImplQueryExceptions(cardCollectionCreationException);
            }
        return newAccount;
    }

    private void handleDBImplQueryExceptions(Exception initialException) throws CleanCodeException {
        LOGGER.log(Level.SEVERE, "Critical error while saving user" + initialException.toString());
        throw new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT);
    }
}
