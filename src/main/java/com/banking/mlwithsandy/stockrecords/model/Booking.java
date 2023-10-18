package com.banking.mlwithsandy.stockrecords.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Booking extends StockRecord {

  private String settlementObligationId;
  private MovementType movementType;
  private ReverasalDetails reversalDetails;
  private BigDecimal unitPriceTradeCurrency;
  private BigDecimal totalPriceTradeCurrency;
  private String remarks;
}
