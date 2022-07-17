package com.example.Dokkaebi.scooter;

import com.example.Dokkaebi.api.FlaskApi;
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
    private final FlaskApi flaskApi;

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
    public ScooterLocationRes checkScooter(@RequestHeader(value = "Authorization") String accessToken) {
        return scooterService.findScooterByMember(accessToken);
    }

    @ApiOperation(value = "대여한 스쿠터 상태확인")
    @GetMapping("/scooter/state")
    public ScooterRentalStateResDto checkState(@RequestHeader(value = "Authorization") String accessToken) {
        return scooterService.checkScooterState(accessToken);
    }

    @ApiOperation(value = "수쿠터 남은 주행 시간확인")
    @GetMapping("/scooter/remainTime")
    public ScooterRemainTimeDto checkRemainTime() throws Exception {
        return flaskApi.requestRemainTime();
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
