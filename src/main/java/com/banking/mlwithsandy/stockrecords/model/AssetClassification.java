package com.banking.mlwithsandy.stockrecords.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class AssetClassification {
    private Boolean isPledged;
    private String pledgingDetails;
    private Boolean isOpposed;
    private String oppositionDetails;
    private Boolean isBlocked;
    private String blockingDetails;
}

