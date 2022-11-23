//1. Realiza una aplicación que genere un fichero de acceso directo
// con registros de una determinada entidad.
// La aplicación debe permitir, al menos, generar altas y consultas.
package com.emi.ex1;

public class Album {
    private int id;
    private String nom;
    private double tiempo;
    public Album() {
    }
    public Album(int id, String nom, double tiempo) {
        this.id = id;
        this.nom = nom;
        this.tiempo = tiempo;
    }
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
    public double getTiempo() {
        return tiempo;
    }
    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }
    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", tiempo =" + tiempo +
                '}';
    }
}
