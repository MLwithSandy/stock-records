package com.banking.mlwithsandy.stockrecords.dto;

import com.banking.mlwithsandy.stockrecords.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embedded;
import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
public class MovementDto extends StockRecord {
    private String settlementObligationId;
    private MovementType movementType;
    @Embedded
    private ReverasalDetails reversalDetails;
    private BigDecimal unitPriceTradeCurrency;
    private BigDecimal totalPriceTradeCurrency;
    private String remarks;
}
