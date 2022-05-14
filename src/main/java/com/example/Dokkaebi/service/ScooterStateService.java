package com.example.Dokkaebi.service;

import com.example.Dokkaebi.Repository.ScooterStateRepo;
import com.example.Dokkaebi.controller.dtos.ScooterStateReqDto;
import com.example.Dokkaebi.domain.ScooterState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScooterStateService {

    private final ScooterStateRepo scooterStateRepo;

    public List<ScooterState> findAll() {
        return scooterStateRepo.findAll();
    }

    @Transactional
    public void enroll(ScooterStateReqDto scooterStateReqDto) throws Exception {
        if(scooterStateRepo.findOne(scooterStateReqDto.getIdentity()).isEmpty()){
            scooterStateRepo.save(scooterStateReqDto.toEntity());
        } else{
            throw new Exception("이미 해당 스쿠터가 등록되어 있습니다.");
        }
    }
}
