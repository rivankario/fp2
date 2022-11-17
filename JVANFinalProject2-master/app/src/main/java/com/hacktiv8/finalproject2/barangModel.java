package com.hacktiv8.finalproject2;

import com.google.firebase.database.PropertyName;

public class barangModel {
    private String dataNama;
    private String dataHarga;
    private String dataStok;
    private String dataKategori;
    private String dataImage;

    public barangModel(){}

    public barangModel(String dataNama, String dataHarga, String dataStok, String dataKategori, String dataImage) {
        this.dataNama = dataNama;
        this.dataHarga = dataHarga;
        this.dataStok = dataStok;
        this.dataKategori = dataKategori;
        this.dataImage = dataImage;
    }

    @PropertyName("namaBarang")
    public String getDataNama() {
        return dataNama;
    }

    public void setDataNama(String dataNama) {
        this.dataNama = dataNama;
    }

    public String getDataHarga() {
        return dataHarga;
    }

    public void setDataHarga(String dataHarga) {
        this.dataHarga = dataHarga;
    }

    public String getDataStok() {
        return dataStok;
    }

    public void setDataStok(String dataStok) {
        this.dataStok = dataStok;
    }

    public String getDataKategori() {
        return dataKategori;
    }

    public void setDataKategori(String dataKategori) {
        this.dataKategori = dataKategori;
    }

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }
}
