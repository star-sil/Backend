package com.example.Dokkaebi.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SocketApi {

    private final SocketService socketService;

    @PostMapping("/api/scooter")
    public void controlScooter(@RequestBody ScooterInfo scooterInfo) {
        socketService.controlScooter(scooterInfo);
    }
}
