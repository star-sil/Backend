package com.example.Dokkaebi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity(name = "stable")
public class Battery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private short bike;
    private String stat;
    private int soc;
    private BigDecimal volt;
    private int temp;
    private BigDecimal lat;
    private BigDecimal lon;
    private Boolean pow;
    private Boolean shoc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getBike() {
        return bike;
    }

    public void setBike(short bike) {
        this.bike = bike;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public int getSoc() {
        return soc;
    }

    public void setSoc(int soc) {
        this.soc = soc;
    }

    public BigDecimal getVolt() {
        return volt;
    }

    public void setVolt(BigDecimal volt) {
        this.volt = volt;
    }

    public Battery(short bike, String stat, int soc, BigDecimal volt, int temp, BigDecimal lat, BigDecimal lon, Boolean pow, Boolean shoc) {
        this.bike = bike;
        this.stat = stat;
        this.soc = soc;
        this.volt = volt;
        this.temp = temp;
        this.lat = lat;
        this.lon = lon;
        this.pow = pow;
        this.shoc = shoc;
    }

    public Battery(short bike, String stat, int soc, int temp, BigDecimal lat, BigDecimal lon, Boolean pow, Boolean shoc) {
        this.bike = bike;
        this.stat = stat;
        this.soc = soc;
        this.temp = temp;
        this.lat = lat;
        this.lon = lon;
        this.pow = pow;
        this.shoc = shoc;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public Boolean getPow() {
        return pow;
    }

    public void setPow(Boolean pow) {
        this.pow = pow;
    }

    public Boolean getShoc() {
        return shoc;
    }

    public void setShoc(Boolean shoc) {
        this.shoc = shoc;
    }
}
