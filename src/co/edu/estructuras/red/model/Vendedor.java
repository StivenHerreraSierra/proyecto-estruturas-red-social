package co.edu.estructuras.red.model;

import java.util.ArrayList;

public class Vendedor {
    private String nombreVendedor;
    private ArrayList<Publicacion> publicaciones;

    public Vendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
        this.publicaciones = new ArrayList<>();
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

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;
        if(object == this)
            return true;

        @SuppressWarnings("unchecked")
        Vendedor vendedor = (Vendedor) object;
        return vendedor.nombreVendedor.equals(this.nombreVendedor);
    }

    @Override
    public String toString() {
        return nombreVendedor;
    }
}
