package com.cleancode.cleancodedbimpl.mappers.cardcollections;

import com.cleancode.bsimpl.dto.cardcollection.CardCollection;
import com.cleancode.cleancodedbimpl.entities.cardcollections.CardCollectionsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardCollectionEntityMapper {
    CardCollectionEntityMapper INSTANCE = Mappers.getMapper(CardCollectionEntityMapper.class);
    @Mappings({
            @Mapping(source="collectionName", target="cardCollectionName"),
            @Mapping(source="collectionReference", target="cardCollectionReference")

    })
    CardCollectionsEntity fromBSCardCollectionToDBImplCardCollection(CardCollection cardCollection);
    @Mappings({
            @Mapping(source="cardCollectionName", target="collectionName"),
            @Mapping(source="cardCollectionReference", target="collectionReference")

    })
    CardCollection fromDBImplCardCollectionToBSCardCollection(CardCollectionsEntity cardCollection);

}
