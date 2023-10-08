package com.banking.mlwithsandy.stockrecords.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import javax.persistence.Embedded;

@Data
@SuperBuilder
public class StockRecord {
    @Id
    private Long id;
    private String accountNo;
    private String isin;
    private String placeOfSafekeeping;
    private String accountCurrency;
    private String tradeCurrency;
    private String safekeepingCurrency;
    @Embedded
    private AssetClassSpecificAttributes assetClassSpecificAttributes;
}
