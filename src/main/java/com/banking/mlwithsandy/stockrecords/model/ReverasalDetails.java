package com.banking.mlwithsandy.stockrecords.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReverasalDetails {
    private String linkedMovementId;
    private String reversalDate;
    private String reversalReason;
}
