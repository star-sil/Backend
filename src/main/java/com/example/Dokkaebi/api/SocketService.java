package com.example.Dokkaebi.api;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import com.example.Dokkaebi.scooter.ScooterStateRepo;
import com.example.Dokkaebi.scooter.entity.ScooterState;
import com.example.Dokkaebi.scooter.entity.Status;
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

    private final ScooterStateRepo scooterStateRepo;

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

    @Transactional
    public void controlScooter(ScooterInfo scooterInfo) {
        String identity = scooterInfo.getIdentity();
        String act = scooterInfo.getAct();
        if (act.equals("on")) {
            String msg = start + identity + fin;
            sendMsg(msg.getBytes());
            ScooterState scooterState = scooterStateRepo.findOne(identity)
                    .orElseThrow(() -> new ApiException(ExceptionEnum.IdentityNotMatched));
            scooterState.changeStatus(Status.DRIVE);
        } else if (act.equals("off")) {
            String msg = end + identity + fin;
            sendMsg(msg.getBytes());
            ScooterState scooterState = scooterStateRepo.findOne(identity)
                    .orElseThrow(() -> new ApiException(ExceptionEnum.IdentityNotMatched));
            scooterState.changeStatus(Status.RENTAL);
        } else {
            throw new ApiException(ExceptionEnum.InvalidAction);
        }
    }

    private void sendMsg(byte[] msg) {
        try {
            Socket socket = new Socket();
            SocketAddress address = new InetSocketAddress(ip,port);
            socket.connect(address);
            OutputStream os = socket.getOutputStream();
            os.write(msg);
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
