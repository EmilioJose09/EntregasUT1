package com.emi.ex4;
/*4. Igualmente haciendo uso de JAXB haciendo uso de anotaciones, para escritura y lectura.*/
import com.emi.ex1.Album;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class jax {
    public static void main(String[] args) {
        Album a1 = new Album (1, "RHLM", 23.5);
        Album a2 = new Album(2, "Afrodisiaco", 33.3);
        Album a3 = new Album(3, "UVST", 42.11);
        Albumes albumes = new Albumes();
        albumes.addAlbumes(a1);
        albumes.addAlbumes(a2);
        albumes.addAlbumes(a3);
        try{
            JAXBContext contexto = JAXBContext.newInstance(albumes.getClass());
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);//bonito XML
            marshaller.marshal(albumes,new File("albumesJAXB.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        Albumes otroAlbumes;
        try{
            JAXBContext contexto2 = JAXBContext.newInstance(Albumes.class);
            Unmarshaller unmarshaller = contexto2.createUnmarshaller();//Unmarshaller te lee el contexto
            otroAlbumes = (Albumes) unmarshaller.unmarshal(new File("albumesJAXB.xml"));
            otroAlbumes.muestraAlbumes();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
