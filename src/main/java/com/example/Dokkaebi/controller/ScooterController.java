package com.example.Dokkaebi.controller;

import com.example.Dokkaebi.controller.dtos.ScooterResponseDto;
import com.example.Dokkaebi.domain.Scooter;
import com.example.Dokkaebi.service.ScooterService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScooterController {
    private final ScooterService scooterService;

    @GetMapping("/scooter")
    public ResponseEntity<Object> findScooters() {
        List<Scooter> Scooters = scooterService.findAllScooter();
        List<ScooterResponseDto> responseDtos = new ArrayList<>();
        for(Scooter scooter : Scooters){
            responseDtos.add(ScooterResponseDto.builder()
                            .bike(scooter.getBike())
                            .lat(scooter.getLat())
                            .lon(scooter.getLon())
                            .soc(scooter.getSoc())
                            .status(scooter.getStatus())
                            .build());
        }
        return new ResponseEntity(new Result(responseDtos), HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
