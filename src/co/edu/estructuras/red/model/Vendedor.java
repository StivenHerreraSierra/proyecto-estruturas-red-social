package co.edu.estructuras.red.model;

import co.edu.estructuras.red.estructuras.arbol.ArbolBinario;
import co.edu.estructuras.red.model.exception.PublicacionException;
import co.edu.estructuras.red.model.exception.VendedorException;

public class Vendedor {
    private String nombreVendedor;
    private ArbolBinario<Producto> productos;
    private ArbolBinario<Publicacion> publicaciones;

    public Vendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
        this.productos = new ArbolBinario<>();
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

    public ArbolBinario<Producto> getProductos() {
        return productos;
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

    public void agregarPublicacion(String nombre, String categoria, double precio) throws VendedorException, PublicacionException {
        if(nombre.isEmpty() || categoria.isEmpty())
            throw new PublicacionException("Error publicando producto: el nombre o categoria no puede estar vacío");

        Producto producto = new Producto(nombre, categoria, precio);

        if(!productos.agregar(producto))
            throw new VendedorException("Error agregando producto: el producto ya se ha registrado.");

        Publicacion publicacion = new Publicacion(producto, this);

        if(!publicaciones.agregar(publicacion))
            throw new VendedorException("Error agregando publicación: la publicación ya se ha registrado.");
    }

    public void comentarPublicacion(String comentario, Publicacion publicacion, Vendedor usuario) throws PublicacionException {
        publicacion.agregarComentario(comentario, usuario);
    }
}
