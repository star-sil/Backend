package com.example.Dokkaebi.scooter;

import com.example.Dokkaebi.scooter.dto.*;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ScooterController {
    private final ScooterService scooterService;

    @PostMapping("/scooter/new")
    public void enrollScooter(@RequestBody ScooterStateReqDto scooterStateReqDto) throws Exception{
        scooterService.enroll(scooterStateReqDto);
    }

    @GetMapping("/scooter")
    public DriveLogDto checkDriveLog(@RequestParam String scooterId, @RequestParam int useCount) {
        return scooterService.checkDriveLog(scooterId, useCount);
    }

    @ApiOperation(value = "스쿠터 위치확인")
    @GetMapping("/scooter/location")
    public ScooterLocationRes checkScooter(@RequestHeader(value = "access_token") String accessToken) {
        return scooterService.findScooterByMember(accessToken);
    }

    @ApiOperation(value = "대여한 스쿠터 상태확인")
    @GetMapping("/scooter/state")
    public ScooterRentalStateResDto checkState(@RequestHeader(value = "access_token") String accessToken) {
        return scooterService.checkScooterState(accessToken);
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
