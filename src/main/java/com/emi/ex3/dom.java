package com.emi.ex3;
//Con el mismo tipo de registro, realiza una aplicación que genere
// un fichero XML DOM y después lo muestre por pantalla.

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.emi.ex1.Album;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class dom {
    public static void main(String[] args) {
        generarXML();
        leerXML();
    }

    public static void generarXML(){
        try {
            DocumentBuilderFactory dba = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dba.newDocumentBuilder();
            DOMImplementation dominin = db.getDOMImplementation();
            Document doc = dominin.createDocument(null,"xml", null);
            ArrayList<Album> albumin = new ArrayList<>();
            albumin.add(new Album(1,"Viceversa", 25.2));
            albumin.add(new Album(2,"Feliz Cumpleaños Ferxxo", 35.2));
            albumin.add(new Album(3,"Microdosis", 32));

            Element raiz = doc.createElement("albumes");
            doc.getDocumentElement().appendChild(raiz);

            Element nodoDatos, nodoAlbumes;
            Text texto;

            for (Album a: albumin){
                nodoAlbumes = doc.createElement("album");
                raiz.appendChild(nodoAlbumes);

                nodoDatos = doc.createElement("id");
                nodoAlbumes.appendChild(nodoDatos);
                texto = doc.createTextNode(String.valueOf(a.getId()));
                nodoDatos.appendChild(texto);

                nodoDatos = doc.createElement("nom");
                nodoAlbumes.appendChild(nodoDatos);
                texto = doc.createTextNode(a.getNom());
                nodoDatos.appendChild(texto);

                nodoDatos = doc.createElement("tiempo");
                nodoAlbumes.appendChild(nodoDatos);
                texto = doc.createTextNode(String.valueOf(a.getTiempo()));
                nodoDatos.appendChild(texto);
            }
            //lo da Ricardo
            Source source = new DOMSource(doc);
            Result resultado = new StreamResult(new File("Albumes.xml"));
            Transformer transform = TransformerFactory.newInstance().newTransformer();
            transform.setOutputProperty("indent","yes");
            transform.transform(source,resultado);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    public static void leerXML(){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("Albumes.xml");
            NodeList nodos;
            nodos = doc.getElementsByTagName("album");//cuantos album hay
            Node nodo;
            Element elemento;
            for (int i = 0; i < nodos.getLength(); i++){
                nodo = nodos.item(i);//nodos tiene todos los album y hijos y vamos al primer nodo en la lista
                elemento = (Element) nodo;
                System.out.println(elemento.getElementsByTagName("id").item(0).getTextContent());
                System.out.println(elemento.getElementsByTagName("nom").item(0).getTextContent());
                System.out.println(elemento.getElementsByTagName("tiempo").item(0).getTextContent());
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}





