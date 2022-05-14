package com.example.Dokkaebi.controller;

import com.example.Dokkaebi.controller.dtos.ScooterStateReqDto;
import com.example.Dokkaebi.controller.dtos.ScooterStateResDto;
import com.example.Dokkaebi.domain.Scooter;
import com.example.Dokkaebi.domain.ScooterState;
import com.example.Dokkaebi.service.ScooterService;
import com.example.Dokkaebi.service.ScooterStateService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScooterController {
    private final ScooterService scooterService;
    private final ScooterStateService scooterStateService;

    @GetMapping("/scooter/state")
    public ResponseEntity<Object> findScooters() {
        List<ScooterStateResDto> responseDtos = new ArrayList<>();
        List<ScooterState> scooterStates = scooterStateService.findAll();
        for (ScooterState scooterState : scooterStates) {
            responseDtos.add(new ScooterStateResDto(scooterState));
        }
        return new ResponseEntity(new Result(responseDtos), HttpStatus.OK);
    }

    @PostMapping("/scooter/new")
    public void enrollScooter(@RequestBody ScooterStateReqDto scooterStateReqDto) throws Exception{
        scooterStateService.enroll(scooterStateReqDto);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class Record<T> {
        private String bike;
        private LocalDateTime startDate;
        private T data;
    }
}
