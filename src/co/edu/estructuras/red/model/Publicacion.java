package co.edu.estructuras.red.model;

import co.edu.estructuras.red.estructuras.lista.ListaDoble;
import co.edu.estructuras.red.model.exception.PublicacionException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * @author Stiven Herrera Sierra
 */
public class Publicacion implements Comparable<Publicacion>, Serializable {
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private final LocalDateTime fechaPublicacion;
    private Producto productoDePublicacion;
    private ListaDoble<Comentario> listaComentarios;
    private ListaDoble<Vendedor> listaMeGusta;
    private final Vendedor propietario;

    public Publicacion(Producto productoDePublicacion, Vendedor propietario) {
        this.fechaPublicacion = LocalDateTime.now();
        this.productoDePublicacion = productoDePublicacion;
        this.listaComentarios = new ListaDoble<>();
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

    public ListaDoble<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(ListaDoble<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
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

    public String getListaMeGustaString() {
        String lista = "";
        Iterator<Vendedor> it = listaMeGusta.iterator();

        while(it.hasNext())
            lista += it.next().getNombreVendedor() + "\n";

        return lista;
    }

    public String getListaComentariosString() {
        String lista = "";
        Iterator<Comentario> it = listaComentarios.iterator();

        while(it.hasNext())
            lista += it.next().toString() + "\n\n";

        return lista;
    }

    public void agregarComentario(String mensaje, Vendedor usuario) throws PublicacionException {
        if(mensaje.isEmpty())
            throw new PublicacionException("Error comentando publicacion: el comentario no puede estar vacio.");

        Comentario comentario = new Comentario(mensaje, usuario, this);
        listaComentarios.agregarfinal(comentario);
    }

    public boolean esPublicadaEntre(LocalDate desde, LocalDate hasta) {
        //Si la publicacion se hizo antes de la fecha "Desde" o después de la fecha "Hasta", no está en entre esas fechas.
        return !(fechaPublicacion.toLocalDate().isBefore(desde) || fechaPublicacion.toLocalDate().isAfter(hasta));
    }
}
