package com.banking.mlwithsandy.stockrecords.dto;

import com.banking.mlwithsandy.stockrecords.model.StockRecord;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;


@Data
@SuperBuilder
@NoArgsConstructor
public class PositionDto extends StockRecord {
    private BigDecimal unitPriceTradeCurrency;
}
