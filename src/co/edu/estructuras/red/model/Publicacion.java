package co.edu.estructuras.red.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Publicacion {
    private final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("yyyy/MM/DD HH:mm:ss:SS");
    private String mensajePublicacion;
    private final String fechaPublicacion;
    private Producto productoDePublicacion;
    private ArrayList<Comentario> comentarioPublicacion;
    private ArrayList<Vendedor> listaMeGusta;

    public Publicacion(String mensajePublicacion, Producto productoDePublicacion) throws ParseException {
        this.mensajePublicacion = mensajePublicacion;
        this.fechaPublicacion = FORMATO_FECHA.format(LocalDateTime.now());
        this.productoDePublicacion = productoDePublicacion;
        this.comentarioPublicacion = new ArrayList<>();
        this.listaMeGusta = new ArrayList<>();
    }

    public String getMensajePublicacion() {
        return mensajePublicacion;
    }

    public void setMensajePublicacion(String mensajePublicacion) {
        this.mensajePublicacion = mensajePublicacion;
    }

    public String getFechaPublicacion() {
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
}
