package com.banking.mlwithsandy.stockrecords.model;

public enum MovementType {
    DEBIT ,
    CREDIT;

    public static MovementType fromString(String movementType) {
        for (MovementType type : MovementType.values()) {
            if (type.name().equalsIgnoreCase(movementType)) {
                return type;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
