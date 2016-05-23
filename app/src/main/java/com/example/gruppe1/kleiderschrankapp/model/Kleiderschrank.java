package com.example.gruppe1.kleiderschrankapp.model;

/**
 * Created by Furkan on 17.05.2016.
 */
public class Kleiderschrank {


    private long id;
    private String bezeichnung;



    public Kleiderschrank() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public Kleiderschrank(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Kleiderschrank{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                '}';
    }

}
