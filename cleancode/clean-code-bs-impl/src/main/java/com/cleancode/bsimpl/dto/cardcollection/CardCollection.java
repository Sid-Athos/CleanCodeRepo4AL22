package com.cleancode.bsimpl.dto.cardcollection;

import com.cleancode.bsimpl.dto.card.Card;

import java.util.List;
import java.util.Objects;

public final class CardCollection {
    private String collectionName;
    private String collectionReference;
    private List<Card> collectionCardList;

    public CardCollection(String collectionName, String collectionReference, List<Card> collectionCardList) {
        this.collectionName = collectionName;
        this.collectionReference = collectionReference;
        this.collectionCardList = collectionCardList;
    }

    public static CardCollection createOne(String collectionName, String collectionReference, List<Card> collectionCardListFromService) {
        return new CardCollection(collectionName, collectionReference, collectionCardListFromService);
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getCollectionReference() {
        return collectionReference;
    }

    public List<Card> getCollectionCardList() {
        return collectionCardList;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setCollectionReference(String collectionReference) {
        this.collectionReference = collectionReference;
    }

    public void setCollectionCardList(List<Card> collectionCardList) {
        this.collectionCardList = collectionCardList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CardCollection) obj;
        return Objects.equals(this.collectionName, that.collectionName) &&
                Objects.equals(this.collectionReference, that.collectionReference) &&
                Objects.equals(this.collectionCardList, that.collectionCardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionName, collectionReference, collectionCardList);
    }

    @Override
    public String toString() {
        return "CardCollection[" +
                "collectionName=" + collectionName + ", " +
                "collectionReference=" + collectionReference + ", " +
                "collectionCardList=" + collectionCardList + ']';
    }

}
