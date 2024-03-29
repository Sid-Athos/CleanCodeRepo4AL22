package com.cleancode.entrypoint.restcontrollers;

import com.cleancode.cleancodeapi.dto.fight.FightRequest;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.CardCollectionCard;
import com.cleancode.domain.pojo.Opponent;
import com.cleancode.domain.ports.in.collectioncard.CollectionCardFighter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fight")
@CrossOrigin
@Api
public class CardFightEntrypoint {

    private final CollectionCardFighter collectionCardFighter;

    public CardFightEntrypoint(CollectionCardFighter collectionCardFighter){
        this.collectionCardFighter = collectionCardFighter;
    }

    @ApiOperation(value = "Fight between two cards",
            response = CardCollectionCard.class,
            notes = "Cards must exists")
    @ApiResponse(code=200, message="Fight Done")
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CardCollectionCard fight(@RequestBody FightRequest fightRequest) throws CleanCodeException {

        Opponent attacker = new Opponent(fightRequest.userNameAttaker, fightRequest.cardAttackerReference);
        Opponent attacked = new Opponent(fightRequest.userNameAttaked, fightRequest.cardAttackedReference);

        return collectionCardFighter.launchFightBetweenTwoCards(attacker, attacked);
    }
}
