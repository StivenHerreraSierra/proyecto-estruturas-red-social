package co.edu.estructuras.red.model;

public class Producto implements Comparable<Producto>{
    private String categoriaProducto;
    private String nombreProducto;

    public Producto(String nombreProducto, String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
        this.nombreProducto = nombreProducto;
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
