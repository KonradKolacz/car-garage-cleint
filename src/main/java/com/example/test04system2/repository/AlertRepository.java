package com.example.test04system2.repository;

import com.example.test04system2.domain.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByCarId(long carId);
    List<Alert> findByGarageId(long garageId);
}
