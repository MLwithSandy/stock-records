package com.banking.mlwithsandy.stockrecords.mapper;

import com.banking.mlwithsandy.stockrecords.dto.PositionDto;
import com.banking.mlwithsandy.stockrecords.model.Movement;
import com.banking.mlwithsandy.stockrecords.model.MovementType;
import com.banking.mlwithsandy.stockrecords.model.Position;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Objects;

@Mapper
public interface PositionMapper {

    PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);

    PositionDto positionToPositionDto(Position position);
    Position positionDtoToPosition(PositionDto positionDto);

    @MovementToPositionMapper
    default Position movement2Position(Movement movement) {
        if (Objects.isNull(movement)) {
            return null;
        } else {
            var positionBuilder = Position.builder();
            positionBuilder.stockRecordKey(movement.getStockRecordKey());
            positionBuilder.unitPriceTradeCurrency(movement.getUnitPriceTradeCurrency());
            positionBuilder.quantity(
                    movement.getMovementType().equals(MovementType.DEBIT)
                            ? movement.getQuantity().multiply(BigDecimal.valueOf(-1))
                            : movement.getQuantity());
            return positionBuilder.build();
        }
    }

}
