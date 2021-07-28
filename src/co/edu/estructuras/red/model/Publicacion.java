package co.edu.estructuras.red.model;

import co.edu.estructuras.red.estructuras.lista.ListaDoble;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Publicacion implements Comparable<Publicacion> {
    private final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private final LocalDateTime fechaPublicacion;
    private Producto productoDePublicacion;
    private ListaDoble<Comentario> comentarioPublicacion;
    private ListaDoble<Vendedor> listaMeGusta;
    private final Vendedor propietario;

    public Publicacion(Producto productoDePublicacion, Vendedor propietario) {
        this.fechaPublicacion = LocalDateTime.now();
        this.productoDePublicacion = productoDePublicacion;
        this.comentarioPublicacion = new ListaDoble<>();
        this.listaMeGusta = new ListaDoble<>();
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

    public ListaDoble<Comentario> getComentarioPublicacion() {
        return comentarioPublicacion;
    }

    public void setComentarioPublicacion(ListaDoble<Comentario> comentarioPublicacion) {
        this.comentarioPublicacion = comentarioPublicacion;
    }

    public ListaDoble<Vendedor> getListaMeGusta() {
        return listaMeGusta;
    }

    public void setListaMeGusta(ListaDoble<Vendedor> listaMeGusta) {
        this.listaMeGusta = listaMeGusta;
    }

    public Vendedor getPropietario() {
        return propietario;
    }

    public int getCantidadMeGusta() {
        return listaMeGusta.getTamanio();
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

    public boolean darMeGusta(Vendedor usuario) {
        if(listaMeGusta.contains(usuario)) {
            listaMeGusta.eliminar(usuario);
            return false;
        }

        listaMeGusta.agregarfinal(usuario);
        return true;
    }

    public boolean dioMeGusta(Vendedor usuario) {
        return listaMeGusta.contains(usuario);
    }
}
