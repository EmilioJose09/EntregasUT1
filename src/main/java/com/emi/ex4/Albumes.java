package com.emi.ex4;

import com.emi.ex1.Album;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Albumes {
    private ArrayList<Album> albumes;

    public Albumes() {
        albumes = new ArrayList<>();
    }

    public void addAlbumes(Album a) {
        albumes.add(a);
    }

    public void muestraAlbumes() {
        for (Album alb : albumes) {//que voy a coger, nombre : de donde
            System.out.println(alb);
        }
    }
}
