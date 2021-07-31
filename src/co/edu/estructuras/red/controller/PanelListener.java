package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.model.Vendedor;

import java.time.LocalDate;

public interface PanelListener {
    public void registrarListener(String nombreUsuario);

    int contarProductosFecha(LocalDate desde, LocalDate hasta);

    Grafo<Vendedor> getListaVendedores();

    Grafo<Vendedor> getListaContactos(Vendedor vendedorSeleccionado);
}
