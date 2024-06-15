package com.example.memomolproject.models;

import androidx.lifecycle.ViewModel;

public class TentangAplikasiModel {
    String judul;
    String deskripsi;

    public TentangAplikasiModel() {
    }

    public TentangAplikasiModel(String judul, String deskripsi) {
        this.judul = judul;
        this.deskripsi = deskripsi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}