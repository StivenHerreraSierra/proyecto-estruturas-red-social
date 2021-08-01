package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.model.Publicacion;
import co.edu.estructuras.red.model.Vendedor;

import java.time.LocalDate;
import java.util.List;

public interface PanelListener {
    void registrarListener(String nombreUsuario);

    int contarProductosFecha(LocalDate desde, LocalDate hasta);

    Grafo<Vendedor> getListaVendedores();

    Grafo<Vendedor> getListaContactos(Vendedor vendedorSeleccionado);

    List<Publicacion> getTopPublicaciones();

    void cantidadMensajesIntercambiados(Vendedor usuario1, Vendedor usuario2);
}
