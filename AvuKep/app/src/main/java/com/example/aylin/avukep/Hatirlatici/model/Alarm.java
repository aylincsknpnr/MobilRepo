package com.example.aylin.avukep.Hatirlatici.model;

/**
 * Created by kbhkn on 11.01.2016.
 */
public class Alarm {
    private int alarmID;
    private String alarmBasligi;
    private String alarmNotu;
    private int alarmDakika;
    private int alarmSaat;
    private int alarmGun;
    private int alarmAy;
    private int alarmYil;

    public Alarm(){
        super();
    }


    public Alarm(int alarmID, String alarmNotu, int alarmDakika, int alarmSaat, int alarmGun, int alarmAy, int alarmYil) {
        super();
        this.alarmID = alarmID;
        this.alarmNotu = alarmNotu;
        this.alarmDakika = alarmDakika;
        this.alarmSaat = alarmSaat;
        this.alarmGun = alarmGun;
        this.alarmAy = alarmAy;
        this.alarmYil = alarmYil;
    }

    public String getAlarmBasligi() {
        return alarmBasligi;
    }

    public void setAlarmBasligi(String alarmBasligi) {
        this.alarmBasligi = alarmBasligi;
    }

    public int getAlarmID() {
        return alarmID;
    }

    public void setAlarmID(int alarmID) {
        this.alarmID = alarmID;
    }

    public String getAlarmNotu() {
        return alarmNotu;
    }

    public void setAlarmNotu(String alarmNotu) {
        this.alarmNotu = alarmNotu;
    }

    public int getAlarmDakika() {
        return alarmDakika;
    }

    public void setAlarmDakika(int alarmDakika) {
        this.alarmDakika = alarmDakika;
    }

    public int getAlarmSaat() {
        return alarmSaat;
    }

    public void setAlarmSaat(int alarmSaat) {
        this.alarmSaat = alarmSaat;
    }

    public int getAlarmGun() {
        return alarmGun;
    }

    public void setAlarmGun(int alarmGun) {
        this.alarmGun = alarmGun;
    }

    public int getAlarmAy() {
        return alarmAy;
    }

    public void setAlarmAy(int alarmAy) {
        this.alarmAy = alarmAy;
    }

    public int getAlarmYil() {
        return alarmYil;
    }

    public void setAlarmYil(int alarmYil) {
        this.alarmYil = alarmYil;
    }
}
