package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.model.Vendedor;

/**
 * @author Stiven Herrera Sierra
 */
public interface ContactosUsuarioPanelListener {
    Grafo<Vendedor> getListaVendedores();

    Grafo<Vendedor> getListaContactos(Vendedor vendedorSeleccionado);

    void cantidadMensajesIntercambiados(Vendedor usuario1, Vendedor usuario2);
}
