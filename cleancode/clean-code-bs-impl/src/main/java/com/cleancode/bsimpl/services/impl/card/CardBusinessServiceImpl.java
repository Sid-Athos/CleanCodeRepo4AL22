package com.cleancode.bsimpl.services.impl.card;

import com.cleancode.bsimpl.dto.card.BusinessCardCreateInfo;
import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeExceptionsEnum;
import com.cleancode.bsimpl.mappers.cards.CardCreateRequestInfoMapper;
import com.cleancode.bsimpl.mappers.users.UserClientInfoMapper;
import com.cleancode.bsimpl.services.impl.user.UserBusinessServiceImpl;
import com.cleancode.bsimpl.services.impl.user.UserEntityMapper;
import com.cleancode.bsimpl.services.interfaces.card.CardBusinessService;
import com.cleancode.bsimpl.utils.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.bsimpl.utils.formatutils.uuid.UUIDFormatter;
import com.cleancode.cleancodeapi.dto.cards.Card;
import com.cleancode.cleancodeapi.dto.cards.CardCreateRequestInfo;
import com.cleancode.cleancodedbimpl.interfaces.cardservices.CardRepositoryService;
import com.cleancode.cleancodedbimpl.interfaces.userservices.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardBusinessServiceImpl implements CardBusinessService {

    private static final Logger LOGGER = Logger.getLogger(UserBusinessServiceImpl.class.getName());
    private CardRepositoryService cardRepositoryService;

    @Autowired
    private void setCardRepositoryService(CardRepositoryService cardRepositoryService){
        this.cardRepositoryService = cardRepositoryService;
    }

    /**
     * @param cardInfo a card from api
     * @return something
     */
    @Override
    public Card createCard(CardCreateRequestInfo cardInfo) throws CleanCodeException {

        BusinessCardCreateInfo businessCardCreateInfo = CardCreateRequestInfoMapper.INSTANCE.fromApiToBs(cardInfo);

        Optional<String> formattedUUIDToBind = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
        if(formattedUUIDToBind.isEmpty()){
            throw new RuntimeException();
        }
        formattedUUIDToBind.ifPresent(businessCardCreateInfo::setBusinessReference);

        /**
         *    Custom exception management with specific status code, check it out
         *    throw new DBIMPLCommunicationException(DBIMPLExceptionEnum.DB_TIMEOUT_EXCEPTION);
         */

        try {
            Long usersEntity = cardRepositoryService.saveCardInDb(CardEntityMapper.INSTANCE.fromBsToDb(businessCardCreateInfo));
            LOGGER.log(Level.INFO, "UserFromApi User : " + cardInfo + " Returned usersEntity : " + usersEntity);
            return CardCreateRequestInfoMapper.INSTANCE.fromBsToApi(businessCardCreateInfo);
        } catch (Exception e){
            handleDBImplQueryExceptions(new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT));
            businessCardCreateInfo.setBusinessReference(null);
        }

        return cardInfo;
    }

    private void handleDBImplQueryExceptions(CleanCodeException dbImplCommunicationException) throws CleanCodeException {
        LOGGER.log(Level.WARNING, "Error while connecting to db : " + dbImplCommunicationException);
        throw dbImplCommunicationException;
    }
}
