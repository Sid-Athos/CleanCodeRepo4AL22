package com.cleancode.persistence.adapters.card;

import com.cleancode.domain.pojo.card.CardCollectionCard;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.persistence.entities.CompositeCardCollectionCardsKey;
import com.cleancode.persistence.entities.cardcollectioncards.CardCollectionCards;
import com.cleancode.persistence.mappers.collectincard.CardCollectionCardMapper;
import com.cleancode.persistence.repositories.collectioncard.CollectionCardsRepository;
import com.jnape.palatable.lambda.adt.Maybe;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class CardCollectionCardSpi implements CardCollectionCardPort {

    private static final Logger LOGGER = Logger.getLogger(CardCollectionCardSpi.class.getName());

    private final CollectionCardsRepository repository;

    public CardCollectionCardSpi(CollectionCardsRepository cardCollectionCardRepository) {
        this.repository = cardCollectionCardRepository;
    }

    @Override
    public List<CardCollectionCard> findAll() {
        return CardCollectionCardMapper.INSTANCE.fromListCardCollectionCardsToListCardCollectionCard(repository.findAll());
    }

    @Override
    public Maybe<CardCollectionCard> findByCardIdAndCollectionId(Long card, Long collection) {
        LOGGER.log(Level.INFO, "Calling DB service findByCardIdAndCollectionId");
        CardCollectionCards foundCollectionCard = repository.findByCardAndCollection(card, collection);
        LOGGER.log(Level.INFO, String.format("Found CollectionCard : %s", foundCollectionCard));
        CardCollectionCard mappedUserToBsUser = CardCollectionCardMapper.INSTANCE.fromCardCollectionCardsToCardCollectionCard(foundCollectionCard);
        return Maybe.maybe(mappedUserToBsUser);
    }

    @Override
    public Maybe<CardCollectionCard> findByCardCollectionCardReference(String reference) {
        LOGGER.log(Level.INFO, "Calling DB service findByCardCollectionCardReference");
        CardCollectionCards foundCollectionCard = repository.findByCardCollectionCardReference(reference);
        LOGGER.log(Level.INFO, String.format("Found CollectionCard : %s", foundCollectionCard));
        CardCollectionCard mappedUserToBsUser = CardCollectionCardMapper.INSTANCE.fromCardCollectionCardsToCardCollectionCard(foundCollectionCard);
        return Maybe.maybe(mappedUserToBsUser);
    }

    @Override
    public CardCollectionCard saveCollectionCard(CardCollectionCard collectionCardToSave) {
        LOGGER.log(Level.INFO, String.format("Calling DB service saveCollectionCard, Card : %s", collectionCardToSave));
        CompositeCardCollectionCardsKey compositeKey = new CompositeCardCollectionCardsKey();
        compositeKey.setCardId(collectionCardToSave.getCardId());
        compositeKey.setCollectionId(collectionCardToSave.getCollectionId());
        CardCollectionCards cardToSave = CardCollectionCardMapper.INSTANCE.fromCardCollectionCardToCardCollectionCards(collectionCardToSave);
        cardToSave.setId(compositeKey);
        var savedCard = repository.save(cardToSave);
        return collectionCardToSave;
    }
}
