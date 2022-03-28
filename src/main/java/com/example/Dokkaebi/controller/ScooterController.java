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

    @GetMapping("/member/scooterRecord/{identity}")
    @ResponseBody
    public List<Record> checkRecord(@PathVariable String identity) {
        List<Scooter> scooters = scooterService.findScooterByIdentity(identity);
        List<ScooterRecordResponseDto> scooterDtos = new ArrayList<>();
        List<Record> records = new ArrayList<>();
        String bike = new String(); LocalDateTime dateTime = LocalDateTime.now(); long cycle = 0;
        for (int i = 0; i < scooters.size(); ++i) {
            if(i == 0){
                bike = scooters.get(i).getBike();
                dateTime = scooters.get(i).getTime();
                cycle = scooters.get(i).getCycle();
                scooterDtos.add(new ScooterRecordResponseDto(scooters.get(i)));
                scooterDtos.clear();
            }
            else if(cycle != scooters.get(i).getCycle()){
                records.add(new Record(bike,dateTime,scooterDtos));
                scooterDtos.clear();
                scooterDtos.add(new ScooterRecordResponseDto(scooters.get(i)));
                bike = scooters.get(i).getBike(); dateTime = scooters.get(i).getTime();
                cycle = scooters.get(i).getCycle();
            }
            else{
                scooterDtos.add(new ScooterRecordResponseDto((scooters.get(i))));
            }
        }
        records.add(new Record(bike,dateTime,scooterDtos));
        return records;
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
