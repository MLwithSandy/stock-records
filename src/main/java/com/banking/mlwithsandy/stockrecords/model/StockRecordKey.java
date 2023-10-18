package com.banking.mlwithsandy.stockrecords.model;

import java.math.BigDecimal;

import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockRecordKey {
    private String accountNo;
    private String securitiesId;
    private String securitiesType;
    private String issueDate;
    private String expiryDate;
    private BigDecimal denomination;
    private BigDecimal strikePrice;
    private BigDecimal interestRate;
    private BigDecimal couponRate;
    private String placeOfSafekeeping;
    private String accountCurrency;
    private String tradeCurrency;
    private String safekeepingCurrency;
    private Boolean isPledged;
    private String pledgingDetails;
    private Boolean isOpposed;
    private String oppositionDetails;
    private Boolean isBlocked;
    private String blockingDetails;
}


