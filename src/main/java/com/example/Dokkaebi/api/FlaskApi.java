package com.example.Dokkaebi.api;

import com.example.Dokkaebi.scooter.dto.ScooterRemainTimeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Component
public class FlaskApi {

    public ScooterRemainTimeDto requestRemainTime() throws Exception{
        URI uri = UriComponentsBuilder
                .fromUriString("http://202.31.200.185:9000")
                .path("/remainTime")
                .encode()
                .build()
                .expand()
                .toUri();
        log.info(uri.toString());
        BatteryData batteryData = new BatteryData(32.4);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ScooterRemainTimeDto> res = restTemplate.postForEntity(uri, batteryData, ScooterRemainTimeDto.class);

        log.info(res.getStatusCode().toString());

        return new ScooterRemainTimeDto(12);
    }

}
