package com.banking.mlwithsandy.stockrecords.model;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@QueryEntity
@Document("movements")
@Data
@SuperBuilder
@NoArgsConstructor
public class Movement extends StockRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;
    @Embedded
    private Audit audit;

    private String settlementObligationId;
    private MovementType movementType;
    @Embedded
    private ReverasalDetails reversalDetails;
    private BigDecimal unitPriceTradeCurrency;
    private BigDecimal totalPriceTradeCurrency;
    private String remarks;
    private String bookingRequestId;
    private Long linkedPositionId;
    private BookingState bookingState;
    private ProcessStatus processStatus;
    private String processStatusReason;
}

