package com.example.syair20.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Contoh {
    public String judul, jenis, isi, gambar;

    public Contoh() {
    }

    public Contoh(String judul, String jenis, String isi, String gambar) {
        this.judul = judul;
        this.jenis = jenis;
        this.isi = isi;
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("judul", judul);
        result.put("jenis", jenis);
        result.put("isi", isi);
        result.put("gambar", gambar);
        return result;
    }
}
