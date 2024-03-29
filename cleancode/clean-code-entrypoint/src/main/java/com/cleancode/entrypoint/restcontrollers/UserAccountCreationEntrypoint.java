package com.cleancode.entrypoint.restcontrollers;

import com.cleancode.domain.pojo.AccountCreationCommand;
import com.cleancode.domain.ports.in.account.AccountCreator;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.cleancodeapi.mappers.CardCollectionMapper;
import com.cleancode.cleancodeapi.dto.cardcollection.CardCollectionResponse;
import com.cleancode.cleancodeapi.dto.user.UserAccountCreationRequest;
import com.cleancode.cleancodeapi.dto.user.UserAccountResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
@CrossOrigin
@Api
public class UserAccountCreationEntrypoint {
    private static final Logger LOGGER = Logger.getLogger(UserAccountCreationEntrypoint.class.getName());
    private final AccountCreator accountCreator;

    public UserAccountCreationEntrypoint(AccountCreator accountCreator){
        this.accountCreator = accountCreator;
    }

    /**
     * @param userCompleteInfoRequest user with userName and deck name
     * @return saved user or throws exception
     */
    @ApiOperation(value = "Adds a user",
            response = UserAccountResponse.class,
            notes = "Customer must not exist")
    @ApiResponse(code=200, message="User Added")
    @PostMapping(value = "/addNewUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserAccountResponse createUserAccount(@RequestBody  UserAccountCreationRequest userCompleteInfoRequest) throws CleanCodeException {
        LOGGER.log(Level.INFO, "Calling createUserAccount");
        AccountCreationCommand accountCreationCommand = AccountCreationCommand.createOne(userCompleteInfoRequest.userName, userCompleteInfoRequest.userCardCollectionName);
        final var createdAccount = accountCreator.saveUserAccount(accountCreationCommand);
        LOGGER.log(Level.INFO, "Executed createUserAccount");
        final CardCollectionResponse apiUserCardCollectionResponse = CardCollectionMapper.INSTANCE.fromDomain(createdAccount.getUserCardCollection());
        return UserAccountResponse.createOneFromBusinessUserAccount(createdAccount.getUserName(), createdAccount.getBusinessReference(), apiUserCardCollectionResponse);
    }
}