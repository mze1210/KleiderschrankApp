package com.example.gruppe1.kleiderschrankapp.model;

public class Kategorie {
    private long id;
    private String bezeichnung;

    public  Kategorie(){

    }

    public Kategorie(long id, String bezeichnung){
        this.id = id;
        this.bezeichnung = bezeichnung;
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

    @Override
    public String toString() {
        return "Kategorie{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                '}';
    }
}
