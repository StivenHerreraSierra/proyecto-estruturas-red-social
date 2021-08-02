package co.edu.estructuras.red.persistencia;

import co.edu.estructuras.red.persistencia.exception.PersistenciaException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Persistencia {
    /**
     * Escribe en un archivo .bin el objeto que recibe.
     * @param object Objeto a guardar.
     * @param ruta Ruta del directorio de destino.
     * @throws IOException Excepción en la escritura.
     */
    public static void writeObject(Object object, File ruta) throws IOException, PersistenciaException {
        if(object == null)
            throw new PersistenciaException("Error escribiendo archivo: el objeto a guardar no puede ser null.");
        if(ruta == null)
            throw new PersistenciaException("Error escribiendo archivo: la ruta no puede ser null.");

        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(ruta.getAbsolutePath()))) {
            writer.writeObject(object);
        }
    }

    /**
     * Lee el objeto que está en el archivo de la ruta indicada.
     * @param ruta Ruta al archivo.
     * @return Objeto leído.
     * @throws IOException Excepción de lectura.
     * @throws ClassNotFoundException Exceción de lectura.
     */
    public static Object readObject(File ruta) throws IOException, ClassNotFoundException, PersistenciaException {
        if(ruta == null)
            throw new PersistenciaException("Error escribiendo archivo: la ruta no puede ser null.");

        Object object = null;
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(ruta.getAbsolutePath()))) {
            object = reader.readObject();
        }
        return object;
    }
}
