package com.cleancode.bsimpl.exceptionsmanagement;

public enum ComponentEnum {
    DB_COMPONENT("BDD"),
    BS_COMPONENT("Logique Métier");

    private final String componentValue;
    public String getComponentValue(){
        return componentValue;
    }
    ComponentEnum(String component) {
        this.componentValue = component;
    }
}
