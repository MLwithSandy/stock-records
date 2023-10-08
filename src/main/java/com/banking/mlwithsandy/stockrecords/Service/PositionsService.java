package com.banking.mlwithsandy.stockrecords.Service;

import com.banking.mlwithsandy.stockrecords.model.Position;
import com.banking.mlwithsandy.stockrecords.repository.PositionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class PositionsService implements StockRecordsService<Position>{

    @Autowired
    private PositionsRepository positionsRepository;

    // write a method which takes Position object and saves it to PositionsRepository
    @Override
    public Position save(Position position) {
        try {
            position.setId(positionsRepository.count() + 1);
        } catch (Exception e) {
            position.setId(1L);
        }
        try {
            var savedPosition = positionsRepository.insert(position);
            return savedPosition;
        } catch (Exception e) {
            log.error("Error while saving position: " + e.getMessage());
            throw e;
        }
    }

    // Write a method which takes an ID and returns a Position object from PositionsRepository
    @Override
    public Position getById(Long id) {
        try {
            var position = positionsRepository.findById(id);
            return position.orElse(null);
        } catch (Exception e) {
            log.error("Error while getting position: " + e.getMessage());
            throw e;
        }
    }
}

