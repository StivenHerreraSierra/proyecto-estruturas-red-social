package co.edu.estructuras.red.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Publicacion implements Comparable<Publicacion> {
    private final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private final LocalDateTime fechaPublicacion;
    private Producto productoDePublicacion;
    private ArrayList<Comentario> comentarioPublicacion;
    private ArrayList<Vendedor> listaMeGusta;

    public Publicacion(Producto productoDePublicacion) {
        this.fechaPublicacion = LocalDateTime.now();
        this.productoDePublicacion = productoDePublicacion;
        this.comentarioPublicacion = new ArrayList<>();
        this.listaMeGusta = new ArrayList<>();
    }

    public String getFechaPublicacion() {
        return FORMATO_FECHA.format(fechaPublicacion);
    }

    public Producto getProductoDePublicacion() {
        return productoDePublicacion;
    }

    public void setProductoDePublicacion(Producto productoDePublicacion) {
        this.productoDePublicacion = productoDePublicacion;
    }

    public ArrayList<Comentario> getComentarioPublicacion() {
        return comentarioPublicacion;
    }

    public void setComentarioPublicacion(ArrayList<Comentario> comentarioPublicacion) {
        this.comentarioPublicacion = comentarioPublicacion;
    }

    public ArrayList<Vendedor> getListaMeGusta() {
        return listaMeGusta;
    }

    public void setListaMeGusta(ArrayList<Vendedor> listaMeGusta) {
        this.listaMeGusta = listaMeGusta;
    }

    @Override
    public int compareTo(Publicacion o) {
        return this.productoDePublicacion.compareTo(o.getProductoDePublicacion());
    }

    @Override
    public String toString() {
        return "Publicacion{" +
                "fechaPublicacion='" + getFechaPublicacion() + '\'' +
                ", productoDePublicacion=" + productoDePublicacion +
                '}';
    }
}
