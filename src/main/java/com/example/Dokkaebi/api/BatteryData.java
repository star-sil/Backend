package com.example.Dokkaebi.api;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BatteryData {
    List<Double> voltage = new ArrayList<>();
    List<Double> current = new ArrayList<>();
    List<Double> temperature = new ArrayList<>();

    public BatteryData(Double data) {
        for (int i = 0; i < 32; i++) {
            voltage.add(data);
            current.add(data);
            temperature.add(data);
        }
    }
}
