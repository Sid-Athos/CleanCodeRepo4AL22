package com.cleancode.domain.ports.in.collectioncard;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.CardCollectionCard;

import java.util.List;

public interface CollectionCardFinder {

    List<CardCollectionCard> findAllCards() throws CleanCodeException;

    CardCollectionCard findByCardCollectionCardReference(String reference) throws CleanCodeException;
}
