package com.example.Dokkaebi.scooter.entity;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import com.example.Dokkaebi.rental.Rental;
import com.google.common.io.ByteStreams;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Scooter {
    @Id @GeneratedValue
    private Long id;
    private String identity;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "scooter")
    private List<Rental> rentals = new ArrayList<>();
    @OneToOne
    private DriveLog activate;

    @Builder
    public Scooter(String identity, Status status) {
        this.identity = identity;
        this.status = status;
    }

    public void changeStatus(Status status) {
        this.status = status;
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }

    /**
     * 비지니스 로직
     */
    public void startDrive(DriveLog driveLog) {
        changeStatus(Status.DRIVE);
        this.activate = driveLog;
    }

    public void endDrive() {
        changeStatus(Status.RENTAL);
        this.activate.endDrive();
        this.activate = null;
    }
}
