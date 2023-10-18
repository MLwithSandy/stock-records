package com.banking.mlwithsandy.stockrecords.model;

import com.querydsl.core.annotations.QueryEmbeddable;
import com.querydsl.core.annotations.QueryEmbedded;
import com.querydsl.core.annotations.QueryEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Embedded;
import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class StockRecord {
    @Embedded
    @QueryEmbedded
    private StockRecordKey stockRecordKey;
    private BigDecimal quantity;
}
