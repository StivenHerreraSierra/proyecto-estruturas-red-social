package co.edu.estructuras.red.model;

import java.io.Serializable;

/**
 * @author Stiven Herrera Sierra
 */
public class Producto implements Comparable<Producto>, Serializable {
    private String categoriaProducto;
    private String nombreProducto;
    private double precioProducto;

    public Producto(String nombreProducto, String categoriaProducto, double precio) {
        this.categoriaProducto = categoriaProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precio;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "categoriaProducto='" + categoriaProducto + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                '}';
    }

    @Override
    public int compareTo(Producto o) {
        return this.nombreProducto.compareTo(o.getNombreProducto());
    }
}
