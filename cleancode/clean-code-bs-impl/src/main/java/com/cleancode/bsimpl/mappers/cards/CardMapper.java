package com.cleancode.bsimpl.mappers.cards;

import com.cleancode.bsimpl.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    Card map(com.cleancode.cleancodeapi.dto.cards.Card card);
    com.cleancode.cleancodeapi.dto.cards.Card map(Card card);
}
