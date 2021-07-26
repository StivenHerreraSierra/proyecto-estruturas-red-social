package co.edu.estructuras.red.model;

import co.edu.estructuras.red.estructuras.Grafo;
import co.edu.estructuras.red.estructuras.exception.GrafoException;

public class Red {
    private Grafo<Vendedor> vendedores;

    public Red () {
        this.vendedores = new Grafo<>();
    }

    public Vendedor registrarVendedor(String nombre) throws GrafoException {
        Vendedor vendedor = new Vendedor(nombre);
        vendedores.agregarNodo(vendedor);

        return vendedor;
    }

    @Override
    public String toString() {
        return "Red{" +
                "vendedores=" + vendedores.toString() +
                '}';
    }
}
