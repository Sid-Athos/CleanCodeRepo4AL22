package com.cleancode.persistence.adapters.cardcollectionservices;

import com.cleancode.domain.dto.cardcollection.CardCollection;
import com.cleancode.domain.ports.out.usercardcollection.CardCollectionPersistencePort;
import com.cleancode.persistence.mappers.cardcollections.CardCollectionEntityMapper;
import com.cleancode.persistence.repositories.cardcollection.CardCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CardCollectionPortImpl implements CardCollectionPersistencePort {

    private final CardCollectionRepository cardCollectionRepository;

    @Autowired
    public CardCollectionPortImpl(CardCollectionRepository cardCollectionRepository) {
        this.cardCollectionRepository = cardCollectionRepository;
    }

    /**
     * @param newUserCardCollection the user newly created card collection
     * @return savedUserCardCollection
     */
    @Override
    public CardCollection saveUserCardCollection(CardCollection newUserCardCollection) {
        return CardCollectionEntityMapper.INSTANCE.fromDBImplCardCollectionToBSCardCollection(cardCollectionRepository.save(CardCollectionEntityMapper.INSTANCE.fromBSCardCollectionToDBImplCardCollection(newUserCardCollection)));
    }
}