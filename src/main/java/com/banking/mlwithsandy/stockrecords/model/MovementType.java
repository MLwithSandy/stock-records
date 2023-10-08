package com.banking.mlwithsandy.stockrecords.model;

public enum MovementType {
    DEBIT ("debit"),
    CREDIT("credit");

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
