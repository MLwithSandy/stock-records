package com.banking.mlwithsandy.stockrecords.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import javax.persistence.Id;
import java.math.BigDecimal;

@Document("movements")
@Data
@Builder
public class Movement {
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
    private MovementType movementType;
    private BigDecimal quantity;
    private String settlementObligationId;
    private String remarks;
    @Embedded
    private ReverasalDetails reversalDetails;
    private Long linkedPositionId;
}
