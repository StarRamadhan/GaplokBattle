package com.akb.gaplokbattle.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "tHistory")
public class History implements Serializable {
    //Membuat kolom NIM sebagai Primary Key
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "Waktu")
    private
    String waktu;

    @ColumnInfo(name = "Status")
    private
    String status;

    @ColumnInfo(name = "Power")
    private
    String power;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
