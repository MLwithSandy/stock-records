package com.banking.mlwithsandy.stockrecords.model;

public enum MovementType {
    DEBIT ("DEBIT"),
    CREDIT("CREDIT");

    private final String movementType;

    MovementType(String movementType) {
        this.movementType = movementType.toUpperCase();
    }

    public String getMovementType() {
        return movementType;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
