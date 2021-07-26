package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.estructuras.Grafo;
import co.edu.estructuras.red.model.Vendedor;

public interface VendedorTabListener {
    public Grafo<Vendedor> actualizarListaTodos(Vendedor usuario);

    public Grafo<Vendedor> actualizarListaSugeridos(Vendedor usuario);

    public void agregarContacto(Vendedor usuario, Vendedor nuevoContacto);

    public Grafo<Vendedor> actualizarListaContactos(Vendedor usuario);
}
