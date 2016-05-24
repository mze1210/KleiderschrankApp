package com.example.gruppe1.kleiderschrankapp.model;

import android.net.Uri;

/**
 * Created by Furkan on 17.05.2016.
 */
public class Klamotte {

    private long id;
    private Kategorie kategorie;
    private Kleiderschrank kleiderschrank;
    private Uri image;

    public Klamotte() {

    }

    public Klamotte(Kategorie kategorie, Kleiderschrank kleiderschrank) {
        this.kategorie = kategorie;
        this.kleiderschrank = kleiderschrank;
    }

    public long getId() {
        return id;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public Kleiderschrank getKleiderschrank() {
        return kleiderschrank;
    }

    public void setKleiderschrank(Kleiderschrank kleiderschrank) {
        this.kleiderschrank = kleiderschrank;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Klamotte{" +
                "id=" + id +
                ", kategorie=" + kategorie +
                ", kleiderschrank=" + kleiderschrank +
                ", image=" + image +
                '}';
    }

}
