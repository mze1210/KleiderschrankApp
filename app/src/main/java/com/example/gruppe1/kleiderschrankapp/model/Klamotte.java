package com.example.gruppe1.kleiderschrankapp.model;

import android.net.Uri;

/**
 * Created by Furkan on 17.05.2016.
 */
public class Klamotte {

    private long id;
    private String bezeichnung;
    private String kategorie;

    private Uri image;


    public Klamotte() {

    }

    public Klamotte(String bezeichnung, String kategorie) {
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

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public Uri getImage() { return image; }

    public void setImage(Uri image) { this.image = image; }
}
