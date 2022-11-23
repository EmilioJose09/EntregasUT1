package com.emi.ex1;
/*1. Realiza una aplicación que genere un fichero de acceso directo con registros de una determinada entidad.
 La aplicación debe permitir, al menos, generar altas y consultas.
*/
import java.io.*;
import java.util.Scanner;

public class Raf {
    private static int id;
    private static String nom;
    private static double tiempo;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int elegir;

        while(true)
        {
            System.out.println("\t MENU \n 1. Alta\n 2. Consultar\n 3. Muestra Duplicados\n 0. Salir");
            elegir = scan.nextInt();

            switch(elegir){

                case 1:
                    System.out.println("Introduce el Identificador de tu album.");
                    id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Nombre del Album");
                    nom = scan.nextLine();
                    System.out.println("Introduce el tiempo que dura el Album");
                    tiempo = scan.nextDouble();
                    scan.nextLine();
                    Album a = new Album(id, nom, tiempo);
                    alta(a);
                    break;
                case 2:
                    System.out.println("Cuál es el ID del Album que quieres consultar?");
                    int buscar = scan.nextInt();
                    Album a2 = new Album(buscar,"",0);
                    consultar(a2);
                    break;

                case 3:
                    System.out.println("Muestra duplicados");
                    leerDupli();
                    break;
                case 0:
                    System.out.println("Hemos acabado MANINNN");
                    System.exit(0);
                    break;
            }

        }
        /*ArrayList<Album> album = new ArrayList<>();
        album.add(new Album(1, "RHLM", 22));
        album.add(new Album(2, "Viceverse", 16));
        album.add(new Album(3, "Microdosis", 19));*/
    }
    public static void alta(Album a){
        try (RandomAccessFile raf = new RandomAccessFile("albumes_raf.dat", "rw")) {
            raf.seek((id - 1) * (2 * (50 + 1) + 2 * Integer.SIZE / 8));
            if (raf.readInt() == id) {
                duplicados();
            } else {
                raf.writeInt(a.getId());
                raf.writeUTF(a.getNom());
                raf.writeDouble(a.getTiempo());
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static void consultar(Album a){
        try (RandomAccessFile raf = new RandomAccessFile("albumes_raf.dat", "rw"))
        {
            raf.seek((a.getId()-1)*(2*(50+1)+2*Integer.SIZE/8));
            int codigo = raf.readInt();
            if(codigo != 0){
                String nombre = raf.readUTF();
                double temp = raf.readDouble();
            }
            System.out.println(
                    "Album{" +
                    "id=" + id +
                    ", nom='" + nom + '\'' +
                    ", tiempo =" + tiempo +
                    '}');
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (EOFException e)
        {
            System.out.println("Este Album no existe MANIN");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static void duplicados(){
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("duplicados.dat", true))){
            dos.writeInt(id);
            dos.writeUTF(nom);
            dos.writeDouble(tiempo);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void leerDupli(){

        try(DataInputStream dis = new DataInputStream(new FileInputStream("duplicados.dat"))){
            while (true) {
                id = dis.readInt();
                nom = dis.readUTF();
                tiempo = dis.readDouble();
                System.out.println(
                        "Album{" +
                                "id=" + id +
                                ", nom='" + nom + '\'' +
                                ", tiempo =" + tiempo +
                                '}');
            }


        } catch (EOFException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
