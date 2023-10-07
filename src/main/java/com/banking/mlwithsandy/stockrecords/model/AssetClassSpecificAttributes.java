package com.banking.mlwithsandy.stockrecords.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
public class AssetClassSpecificAttributes {
    private String expiryDate;
    private String strikePrice;
    private String issueDate;
    private BigDecimal interestRate;
    private BigDecimal couponRate;
}

