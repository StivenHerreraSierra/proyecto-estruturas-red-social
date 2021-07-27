package co.edu.estructuras.red.model;

import co.edu.estructuras.red.estructuras.arbol.ArbolBinario;
import co.edu.estructuras.red.estructuras.exception.NodoException;

public class Vendedor {
    private String nombreVendedor;
    private ArbolBinario<Publicacion> publicaciones;

    public Vendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
        this.publicaciones = new ArbolBinario<>();
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public ArbolBinario<Publicacion> getPublicaciones() {
        return publicaciones;
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

    public void agregarPublicacion(String nombre, String categoria) throws NodoException {
        Producto producto = new Producto(nombre, categoria);
        Publicacion publicacion = new Publicacion(producto);

        publicaciones.agregar(publicacion);
    }
}
