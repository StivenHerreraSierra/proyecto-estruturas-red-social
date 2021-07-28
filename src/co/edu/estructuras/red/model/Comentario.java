package co.edu.estructuras.red.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comentario {
    private final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private String mensaje;
    private final Vendedor propietario;
    private final Publicacion publicacion;
    private final LocalDateTime fechaPublicacion;

    public Comentario(String mensaje, Vendedor propietario, Publicacion publicacion) {
        this.mensaje = mensaje;
        this.propietario = propietario;
        this.publicacion = publicacion;
        this.fechaPublicacion = LocalDateTime.now();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Vendedor getPropietario() {
        return propietario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public String getFechaPublicacion() {
        return FORMATO_FECHA.format(fechaPublicacion);
    }

    @Override
    public String toString() {
        return propietario.getNombreVendedor() + " - " + getFechaPublicacion() + "\n"
                + mensaje;
    }
}
