package org.ej3b.models;

public class Usuarios {
    private int id;
    private String nom;
    private String pwsd;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPwsd() {
        return pwsd;
    }

    public void setPwsd(String pwsd) {
        this.pwsd = pwsd;
    }
}
