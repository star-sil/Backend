package com.example.Dokkaebi.api;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import com.example.Dokkaebi.rental.JpaRentalRepo;
import com.example.Dokkaebi.rental.Rental;
import com.example.Dokkaebi.scooter.Repo.DriveLogRepo;
import com.example.Dokkaebi.scooter.Repo.ScooterRepo;
import com.example.Dokkaebi.scooter.entity.DriveLog;
import com.example.Dokkaebi.scooter.entity.Scooter;
import com.google.common.io.ByteStreams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final ScooterRepo scooterRepo;
    private final DriveLogRepo driveLogRepo;
    private final JpaRentalRepo jpaRentalRepo;

    @Value("${host.ip}")
    private String ip;
    @Value("${host.port}")
    private int port;
    @Value("${protocol.start}")
    private String start;
    @Value("${protocol.end}")
    private String end;
    @Value("${protocol.fin}")
    private String fin;


    /**
     * TODO 소켓 도메인을 하나 파자 -> 엔티티도 추가
     */
    @Transactional
    public void controlScooter(ScooterInfo scooterInfo) {

        String identity = scooterInfo.getIdentity();
        String act = scooterInfo.getAct();

        Scooter scooter = scooterRepo.findOne(identity)
                .orElseThrow(() -> new ApiException(ExceptionEnum.IdentityNotMatched));
        Integer nextDriveCount = driveLogRepo.countDriveLogByScooter(scooter) + 1;

        if (act.equals("on")) {
            Rental rental = jpaRentalRepo.findById(scooterInfo.getRentalId())
                    .orElseThrow(()->new ApiException(ExceptionEnum.RentalNotMatched));
            DriveLog driveLog = new DriveLog(nextDriveCount, scooter, rental);
            driveLogRepo.save(driveLog);
            sendMsg(start+scooter.getIdentity()+fin);
            scooter.startDrive(driveLog);
        } else if (act.equals("off")) {
            sendMsg(end+scooter.getIdentity()+fin);
            scooter.endDrive();
        } else {
            throw new ApiException(ExceptionEnum.InvalidAction);
        }
    }

    // TODO 메세지를 전송하고 응답 메시지를 받아서 처리하는 것까지 완료하기
    public void sendMsg(String msg) {
        byte[] send = msg.getBytes();
        try {
            Socket socket = new Socket();
            SocketAddress address = new InetSocketAddress(ip, port);
            socket.connect(address);
            OutputStream os = socket.getOutputStream();
            os.write(send);
            os.flush();
            InputStream is = socket.getInputStream();
            byte[] reply = new byte[5];
            ByteStreams.read(is, reply, 0, reply.length);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiException(ExceptionEnum.InvalidConnected);
        }
    }
}
