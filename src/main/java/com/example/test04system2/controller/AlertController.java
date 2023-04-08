package com.example.test04system2.controller;

import com.example.test04system2.dto.AlertDto;
import com.example.test04system2.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService alertService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AlertDto>> findAll() {
        return new ResponseEntity<>(alertService.findAll().stream()
                .map(alert -> modelMapper.map(alert, AlertDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/cars/{carId}")
    public ResponseEntity<List<AlertDto>> findByCarId(@PathVariable("carId") long carId) {
        return new ResponseEntity<>(alertService.findByCarId(carId).stream()
                .map(alert -> modelMapper.map(alert, AlertDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/garages/{garageId}")
    public ResponseEntity<List<AlertDto>> findByGarageId(@PathVariable("garageId") long garageId) {
        return new ResponseEntity<>(alertService.findByGarageId(garageId).stream()
                .map(alert -> modelMapper.map(alert, AlertDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
