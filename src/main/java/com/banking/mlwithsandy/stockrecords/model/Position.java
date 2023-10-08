package com.banking.mlwithsandy.stockrecords.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import java.math.BigDecimal;

@Document("positions")
@Data
@SuperBuilder
public class Position extends StockRecord {
    private BigDecimal quantity;
}
