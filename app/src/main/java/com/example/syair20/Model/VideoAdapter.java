package com.example.syair20.Model;

public class VideoAdapter {
    String url, judul, deskripsi;

    public VideoAdapter() {
    }

    public VideoAdapter(String url, String judul, String deskripsi) {
        this.url = url;
        this.judul = judul;
        this.deskripsi = deskripsi;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
