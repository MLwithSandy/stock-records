package com.banking.mlwithsandy.stockrecords.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
public class SecurtiesKeyAttributes {
    private String securitiesType;
    private String issueDate;
    private String expiryDate;
    private BigDecimal denomination;
    private BigDecimal strikePrice;
    private BigDecimal interestRate;
    private BigDecimal couponRate;
}

