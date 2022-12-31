package com.cleancode.domain.enums.enums.cardpackrarities;

public enum CardPackRaritiesEnum {
    SILVER("SILVER"),
    DIAMOND("DIAMOND");

    private final String cardPackRarityCode;

    CardPackRaritiesEnum(String cardPackRarityCode) {
        this.cardPackRarityCode = cardPackRarityCode;
    }
    public String getCardPackRarityCode() {
        return cardPackRarityCode;
    }

    @Override
    public String toString() {
        return "CardPackRarity{" +
                "cardPackRarityCode='" + cardPackRarityCode + '\'' +
                '}';
    }
}