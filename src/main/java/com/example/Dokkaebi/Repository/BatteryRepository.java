package com.example.Dokkaebi.Repository;

import com.example.Dokkaebi.domain.Battery;
import io.netty.buffer.ByteBuf;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BatteryRepository extends JpaRepository<Battery,Integer>{

}
