package com.banking.mlwithsandy.stockrecords.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("bookingRequest")
@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;
    @Indexed(unique = true)
    private String uniqueBookingRequestId;
    private Audit audit;
    private List<Booking> bookingsList;
    private ProcessStatus processStatus;
    private String processStatusReason;
}



