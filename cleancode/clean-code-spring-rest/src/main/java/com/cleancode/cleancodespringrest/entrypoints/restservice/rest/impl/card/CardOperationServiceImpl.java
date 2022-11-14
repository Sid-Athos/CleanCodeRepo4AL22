package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.impl.card;

import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.bsimpl.services.interfaces.card.CardBusinessService;
import com.cleancode.cleancodeapi.dto.cards.Card;
import com.cleancode.cleancodeapi.mappers.card.CardMapper;
import com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.card.CardOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
@RequestMapping("/card/")
@CrossOrigin
@Api
public class CardOperationServiceImpl implements CardOperationService {

    private static final Logger LOGGER = Logger.getLogger(CardOperationServiceImpl.class.getName());
    private CardBusinessService cardBusinessService;

    @Autowired
    private void setCardBusinessService(CardBusinessService cardBusinessService){
        this.cardBusinessService = cardBusinessService;
    }

    @ApiOperation(value = "Create a card",
            response = Card.class,
            notes = "Customer must not exist")
    @ApiResponse(code=200, message="Card Created")
    @PutMapping(value = "/saveCard", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Card saveCard(@RequestBody Card card) throws CleanCodeException {
        return CardMapper.INSTANCE.fromBsToApi(cardBusinessService.saveCard(CardMapper.INSTANCE.fromApiToBs(card)));
    }
}