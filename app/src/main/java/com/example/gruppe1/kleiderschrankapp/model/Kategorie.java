package com.example.gruppe1.kleiderschrankapp.model;

/**
 * Created by Furkan on 24.05.2016.
 */
public class Kategorie {
    private long id;
    private String bezeichnung;

    public long getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public String toString() {
        return "Kategorie{" +
                "bezeichnung='" + bezeichnung + '\'' +
                '}';
    }
}
