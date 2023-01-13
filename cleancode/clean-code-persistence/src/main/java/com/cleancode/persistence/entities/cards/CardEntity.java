package com.cleancode.persistence.entities.cards;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "CARDS")
public class CardEntity implements Serializable {
    public CardEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length=32)
    private String cardReference;

    @Column(nullable = false, length=32)
    private String cardRarity;

    @Column(nullable = false, length=32)
    private String cardSpecialty;

    @Column(nullable = false, length=32)
    private String cardName;

    @Column(nullable = false)
    private int xp;

    @Column(nullable = false)
    private int level;

    @OneToMany(mappedBy = "id")
    private List<CardEntity> collectionCardList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardEntity that = (CardEntity) o;

        if (xp != that.xp) return false;
        if (level != that.level) return false;
        if (!id.equals(that.id)) return false;
        if (!cardReference.equals(that.cardReference)) return false;
        if (!cardRarity.equals(that.cardRarity)) return false;
        if (!cardSpecialty.equals(that.cardSpecialty)) return false;
        if (!cardName.equals(that.cardName)) return false;
        return collectionCardList.equals(that.collectionCardList);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + cardReference.hashCode();
        result = 31 * result + cardRarity.hashCode();
        result = 31 * result + cardSpecialty.hashCode();
        result = 31 * result + cardName.hashCode();
        result = 31 * result + xp;
        result = 31 * result + level;
        result = 31 * result + collectionCardList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CardEntity{" +
                "id=" + id +
                ", cardReference='" + cardReference + '\'' +
                ", cardRarity='" + cardRarity + '\'' +
                ", cardSpecialty='" + cardSpecialty + '\'' +
                ", cardName='" + cardName + '\'' +
                ", xp=" + xp +
                ", level=" + level +
                '}';
    }
}
