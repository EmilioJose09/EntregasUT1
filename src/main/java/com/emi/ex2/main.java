package com.emi.ex2;
//2. Realiza una aplicación, haciendo uso de las clases de NIO,
// incluyendo buffers y canales, para hacer una copia del fichero de acceso directo
// generado en la anterior aplicación.
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;
public class main {
    public static void main(String[] args) {

        FileChannel fileChannelEsc;
        ByteBuffer buffer;

        try(RandomAccessFile raf = new RandomAccessFile("albumes_raf.dat", "rw");
        FileChannel fileChannelLec = raf.getChannel())
        {
            Path path = Paths.get("copiaAlbumes.dat");
            Set <StandardOpenOption> opciones = new HashSet<>();
            opciones.add(StandardOpenOption.CREATE);

            opciones.add(StandardOpenOption.APPEND);
            fileChannelEsc = FileChannel.open(path, opciones);
            buffer = ByteBuffer.allocate(911);

            while(fileChannelLec.read(buffer) > 0)//va leyendo 911
            {
                buffer.flip();//de escritura a Lectura
                fileChannelEsc.write(buffer);
                buffer.rewind();
            }
            fileChannelEsc.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
