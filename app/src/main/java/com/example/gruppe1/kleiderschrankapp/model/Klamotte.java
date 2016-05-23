package com.example.gruppe1.kleiderschrankapp.model;

import android.net.Uri;

/**
 * Created by Furkan on 17.05.2016.
 */
public class Klamotte {

    private long id;
    private String bezeichnung;
    private Kategorie kategorie;

    private Uri image;


    public Klamotte() {

    }

    public Klamotte(String bezeichnung, Kategorie kategorie) {
        this.bezeichnung = bezeichnung;
        this.kategorie = kategorie;
    }

    public long getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public Uri getImage() { return image; }

    public void setImage(Uri image) { this.image = image; }

    @Override
    public String toString() {
        return "Klamotte{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", kategorie='" + kategorie + '\'' +
                '}';
    }
}
