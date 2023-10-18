package com.banking.mlwithsandy.stockrecords.model;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;

@QueryEntity
@Document("positions")
@Data
@SuperBuilder
@NoArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name = "stockRecordKey", def = "{'stockRecordKey' : 1}", unique = true)
})
public class Position extends StockRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;
    @Embedded
    private Audit audit;
    private Long positionSequenceId;
    private BigDecimal unitPriceTradeCurrency;
}
