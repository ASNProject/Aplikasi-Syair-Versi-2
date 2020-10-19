package com.example.syair20.Model;

public class Materi {
    private String judul, isi, link;

    public Materi() {
    }

    public Materi(String judul, String isi, String link) {
        this.judul = judul;
        this.isi = isi;
        this.link = link;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
