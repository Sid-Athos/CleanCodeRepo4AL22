package com.cleancode.bsimpl.dto.user;


import com.cleancode.bsimpl.Address;
import com.cleancode.bsimpl.CardCollection;

import java.sql.Timestamp;
import java.util.List;

public class BusinessUserClientInfo {

    protected final Long technicalId;

    private String businessReference;

    private Timestamp clientCreationDate;

    private final Address clientAddress;
    private List<CardCollection> userCardCollectionsList;

    public BusinessUserClientInfo(Long technicalId, String businessReference, Timestamp clientCreationDate, Address clientAddress, List<CardCollection> userCardCollectionsList) {
        this.technicalId = technicalId;
        this.businessReference = businessReference;
        this.clientCreationDate = clientCreationDate;
        this.clientAddress = clientAddress;
        setUserCardCollectionsList(userCardCollectionsList);
    }

    public Long getTechnicalId() {
        return technicalId;
    }

    public String getBusinessReference() {
        return businessReference;
    }

    public void setBusinessReference(String businessReference) {
        this.businessReference = businessReference;
    }

    public Timestamp getClientCreationDate() {
        return clientCreationDate;
    }

    public void setClientCreationDate(Timestamp clientCreationDate) {
        this.clientCreationDate = clientCreationDate;
    }

    public Address getClientAddress() {
        return clientAddress;
    }

    public List<CardCollection> getUserCardCollectionsList() {
        return userCardCollectionsList;
    }

    public void setUserCardCollectionsList(List<CardCollection> userCardCollectionsList) {
        this.userCardCollectionsList = userCardCollectionsList;
    }
}