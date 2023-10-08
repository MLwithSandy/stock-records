package com.banking.mlwithsandy.stockrecords.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import javax.persistence.Id;
import java.math.BigDecimal;

@Document("movements")
@Data
@SuperBuilder
public class Movement extends StockRecord {

    private String settlementObligationId;
    private MovementType movementType;
    private String remarks;
    @Embedded
    private ReverasalDetails reversalDetails;
    private Long linkedPositionId;
    private BigDecimal quantity;
}
