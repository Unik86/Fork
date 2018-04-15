package com.fork.base.model.enums;

public enum SportTypes {

    FOOTBALL("Football"),
    TENNIS("Tennis");

    private String type;

    SportTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static SportTypes getSportType(String typeStr){
        for(SportTypes type : SportTypes.values()){
            if(type.getType().equals(typeStr))
                return type;
        }

        return null;
    }
}
