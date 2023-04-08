package com.example.test04system2.service;

import com.example.test04system2.domain.Alert;
import com.example.test04system2.repository.AlertRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "alert")
    public void listenAndSaveAlert(String alertJson) {
        try {
            alertRepository.save(objectMapper.readValue(alertJson, Alert.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Alert> findAll() {
        return alertRepository.findAll();
    }

    public List<Alert> findByCarId(long carId) {
        return alertRepository.findByCarId(carId);
    }

    public List<Alert> findByGarageId(long garageId) {
        return alertRepository.findByGarageId(garageId);
    }

}
