package com.example.test04system2.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AlertDto {

    private Long id;
    private String ip;
    private LocalDate date;
    private long carId;
    private long garageId;
}
