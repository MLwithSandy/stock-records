package com.banking.mlwithsandy.stockrecords.Service;

import com.banking.mlwithsandy.stockrecords.model.Movement;
import com.banking.mlwithsandy.stockrecords.model.MovementType;
import com.banking.mlwithsandy.stockrecords.model.Position;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

  @Override
  public List<Position> book(List<Movement> movementsList) {
    var positionList =
        movementsList.parallelStream()
            .map(this::generatePosition)
            .filter(Objects::nonNull)
            .toList();

    return positionList;
  }

  private Position generatePosition(Movement movement) {
    if (Objects.isNull(movement)) {
      return null;
    } else {
      var positionBuilder = Position.builder();
      positionBuilder.accountNo(movement.getAccountNo());
      positionBuilder.isin(movement.getIsin());
      positionBuilder.placeOfSafekeeping(movement.getPlaceOfSafekeeping());
      positionBuilder.accountCurrency(movement.getAccountCurrency());
      positionBuilder.tradeCurrency(movement.getTradeCurrency());
      positionBuilder.safekeepingCurrency(movement.getSafekeepingCurrency());
      positionBuilder.assetClassSpecificAttributes(
          Objects.isNull(movement.getAssetClassSpecificAttributes())
              ? null
              : movement.getAssetClassSpecificAttributes());
      positionBuilder.quantity(
          movement.getMovementType().equals(MovementType.DEBIT)
              ? movement.getQuantity().multiply(BigDecimal.valueOf(-1))
              : movement.getQuantity());

      return positionBuilder.build();
    }
  }
}
