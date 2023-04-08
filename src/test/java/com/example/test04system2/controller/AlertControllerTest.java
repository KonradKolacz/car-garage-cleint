package com.example.test04system2.controller;

import com.example.test04system2.domain.Alert;
import com.example.test04system2.repository.AlertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AlertControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AlertRepository alertRepository;

    private static final long CAR_ONE_ID = 3;
    private static final long CAR_TWO_ID = 4;
    private static final long GARAGE_ID = 1;


    @BeforeEach
    void setUp() {
        Alert alert = new Alert(1L, "106.249.53.150", LocalDate.now(), CAR_ONE_ID, GARAGE_ID);
        Alert alert2 = new Alert(2L, "106.249.53.151", LocalDate.now(), CAR_TWO_ID, GARAGE_ID);
        Alert alert3 = new Alert(3L, "106.249.53.152", LocalDate.now(), CAR_TWO_ID, GARAGE_ID);
        Alert alert4 = new Alert(4L, "106.249.53.153", LocalDate.now(), CAR_ONE_ID, GARAGE_ID);
        alertRepository.save(alert);
        alertRepository.save(alert2);
        alertRepository.save(alert3);
        alertRepository.save(alert4);
    }

    @Test
    public void shouldGetAlertsByCarId() throws Exception {
        this.mockMvc.perform(get("/alerts/cars/{carId}", CAR_ONE_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].garageId").value(GARAGE_ID))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldGetAlertsByGarageId() throws Exception {

        this.mockMvc.perform(get("/alerts/garages/{garageId}", GARAGE_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].carId").value(CAR_ONE_ID))
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void shouldGetAllAlerts() throws Exception {
        this.mockMvc.perform(get("/alerts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

}