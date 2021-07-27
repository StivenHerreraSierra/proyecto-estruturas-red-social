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
    private final Vendedor propietario;

    public Publicacion(Producto productoDePublicacion, Vendedor propietario) {
        this.fechaPublicacion = LocalDateTime.now();
        this.productoDePublicacion = productoDePublicacion;
        this.comentarioPublicacion = new ArrayList<>();
        this.listaMeGusta = new ArrayList<>();
        this.propietario = propietario;
    }

    public String getFechaPublicacionString() {
        return FORMATO_FECHA.format(fechaPublicacion);
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
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

    public Vendedor getPropietario() {
        return propietario;
    }

    public int getCantidadMeGusta() {
        return listaMeGusta.size();
    }

    @Override
    public int compareTo(Publicacion o) {
        return this.fechaPublicacion.compareTo(o.getFechaPublicacion());
    }

    @Override
    public String toString() {
        return "Publicacion{" +
                "fechaPublicacion='" + getFechaPublicacionString() + '\'' +
                ", productoDePublicacion=" + productoDePublicacion +
                '}';
    }
}
