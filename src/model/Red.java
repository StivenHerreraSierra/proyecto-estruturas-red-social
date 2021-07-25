package model;

import estructuras.Grafo;
import estructuras.exception.GrafoException;

public class Red {
    private Grafo<Vendedor> vendedores;

    public Red () {
        this.vendedores = new Grafo<>();
    }

    public void registrarVendedor(String nombre) throws GrafoException {
        Vendedor vendedor = new Vendedor(nombre);
        vendedores.agregarNodo(vendedor);
    }

    @Override
    public String toString() {
        return "Red{" +
                "vendedores=" + vendedores.toString() +
                '}';
    }
}
