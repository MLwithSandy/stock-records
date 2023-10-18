package com.banking.mlwithsandy.stockrecords.utility;

import com.banking.mlwithsandy.stockrecords.model.QStockRecordKey;
import com.banking.mlwithsandy.stockrecords.model.StockRecordKey;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class SearchPredicate {
    public static List<BooleanExpression> generateStockRecordsKeySearchPredicateEq(QStockRecordKey qStockRecordKey, StockRecordKey stockRecordKey) {

        List<BooleanExpression> booleanExpressions = new ArrayList<>();

        if (Objects.nonNull(stockRecordKey)) {
            booleanExpressions.add(Objects.isNull(stockRecordKey.getAccountNo())? null : qStockRecordKey.accountNo.eq(stockRecordKey.getAccountNo()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getSecuritiesId())? null : qStockRecordKey.securitiesId.eq(stockRecordKey.getSecuritiesId()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getSecuritiesType())? null : qStockRecordKey.securitiesType.eq(stockRecordKey.getSecuritiesType()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getIssueDate())? null : qStockRecordKey.issueDate.eq(stockRecordKey.getIssueDate()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getExpiryDate())? null : qStockRecordKey.expiryDate.eq(stockRecordKey.getExpiryDate()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getDenomination())? null : qStockRecordKey.denomination.eq(stockRecordKey.getDenomination()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getStrikePrice())? null : qStockRecordKey.strikePrice.eq(stockRecordKey.getStrikePrice()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getInterestRate())? null : qStockRecordKey.interestRate.eq(stockRecordKey.getInterestRate()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getCouponRate())? null : qStockRecordKey.couponRate.eq(stockRecordKey.getCouponRate()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getPlaceOfSafekeeping())? null : qStockRecordKey.placeOfSafekeeping.eq(stockRecordKey.getPlaceOfSafekeeping()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getAccountCurrency())? null : qStockRecordKey.accountCurrency.eq(stockRecordKey.getAccountCurrency()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getTradeCurrency())? null : qStockRecordKey.tradeCurrency.eq(stockRecordKey.getTradeCurrency()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getSafekeepingCurrency())? null : qStockRecordKey.safekeepingCurrency.eq(stockRecordKey.getSafekeepingCurrency()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getIsPledged())? null : qStockRecordKey.isPledged.eq(stockRecordKey.getIsPledged()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getPledgingDetails())? null : qStockRecordKey.pledgingDetails.eq(stockRecordKey.getPledgingDetails()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getIsOpposed())? null : qStockRecordKey.isOpposed.eq(stockRecordKey.getIsOpposed()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getOppositionDetails())? null : qStockRecordKey.oppositionDetails.eq(stockRecordKey.getOppositionDetails()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getIsBlocked())? null : qStockRecordKey.isBlocked.eq(stockRecordKey.getIsBlocked()));
            booleanExpressions.add(Objects.isNull(stockRecordKey.getBlockingDetails())? null : qStockRecordKey.blockingDetails.eq(stockRecordKey.getBlockingDetails()));
        }
        return booleanExpressions;
    }
}
