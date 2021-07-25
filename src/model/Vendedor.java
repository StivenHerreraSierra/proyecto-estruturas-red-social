package model;

import java.util.ArrayList;

public class Vendedor {
    private String nombreVendedor;
    private ArrayList<Publicacion> publicaciones;
    private Vendedor[] contactos;

    public Vendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
        this.publicaciones = new ArrayList<>();
        this.contactos = new Vendedor[10];
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public Vendedor[] getContactos() {
        return contactos;
    }

    public void setContactos(Vendedor[] contactos) {
        this.contactos = contactos;
    }
}
