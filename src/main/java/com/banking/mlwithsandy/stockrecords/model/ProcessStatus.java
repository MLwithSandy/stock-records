package com.banking.mlwithsandy.stockrecords.model;

public enum ProcessStatus {
    INITIAL ("INITIAL"),
    ERROR ("ERROR"),
    SUCCESS("SUCCESS");

    private final String processStatus;

    ProcessStatus(String processStatus) {
        this.processStatus = processStatus.toUpperCase();
    }

    public String getMovementType() {
        return processStatus;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
