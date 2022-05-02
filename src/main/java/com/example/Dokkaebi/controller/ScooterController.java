package com.example.Dokkaebi.controller;

import com.example.Dokkaebi.controller.dtos.ScooterRecordResponseDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
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
            responseDtos.add(new ScooterResponseDto(scooter));
        }
        return new ResponseEntity(new Result(responseDtos), HttpStatus.OK);
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
