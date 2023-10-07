package com.banking.mlwithsandy.stockrecords.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import java.math.BigDecimal;

@Document("positions")
@Data
@Builder
public class Position {
    @Id
    private Long id;
    private String isin;
    private String accountNo;
    private String placeOfSafekeeping;
    private String accountCurrency;
    private String tradeCurrency;
    private String safekeepingCurrency;
    @Embedded
    private AssetClassSpecificAttributes assetClassSpecificAttributes;
    private BigDecimal quantity;
}
