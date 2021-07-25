package model;

public class Comentario {
    private String mensaje;
    private final Vendedor propietario;
    private final Publicacion publicacion;

    public Comentario(String mensaje, Vendedor propietario, Publicacion publicacion) {
        this.mensaje = mensaje;
        this.propietario = propietario;
        this.publicacion = publicacion;
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

    @Override
    public String toString() {
        return "Comentario{" +
                "mensaje='" + mensaje + '\'' +
                ", propietario=" + propietario +
                ", publicacion=" + publicacion +
                '}';
    }
}
