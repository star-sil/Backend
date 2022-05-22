package com.example.Dokkaebi.scooter;

import com.example.Dokkaebi.scooter.dto.DriveLogDto;
import com.example.Dokkaebi.scooter.dto.ScooterStateReqDto;
import com.example.Dokkaebi.scooter.dto.ScooterStateResDto;
import com.example.Dokkaebi.scooter.entity.ScooterState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScooterController {
    private final ScooterService scooterService;

    @GetMapping("/scooter/state")
    public ResponseEntity<Object> findScooters() {
        List<ScooterStateResDto> responseDtos = new ArrayList<>();
        List<ScooterState> scooterStates = scooterService.findAll();
        for (ScooterState scooterState : scooterStates) {
            responseDtos.add(new ScooterStateResDto(scooterState));
        }
        return new ResponseEntity(new Result(responseDtos), HttpStatus.OK);
    }

    @PostMapping("/scooter/new")
    public void enrollScooter(@RequestBody ScooterStateReqDto scooterStateReqDto) throws Exception{
        scooterService.enroll(scooterStateReqDto);
    }

    @GetMapping("/scooter")
    public DriveLogDto checkDriveLog(@RequestParam String scooterId, @RequestParam int useCount) {
        return scooterService.checkDriveLog(scooterId, useCount);
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
