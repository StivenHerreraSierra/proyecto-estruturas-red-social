package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.model.Vendedor;

public interface ContactosUsuarioPanelListener {
    Grafo<Vendedor> getListaVendedores();

    Grafo<Vendedor> getListaContactos(Vendedor vendedorSeleccionado);
}
