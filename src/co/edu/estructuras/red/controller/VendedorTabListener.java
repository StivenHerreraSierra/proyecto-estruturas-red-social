package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.estructuras.arbol.ArbolBinario;
import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.model.Publicacion;
import co.edu.estructuras.red.model.Vendedor;

public interface VendedorTabListener {
    public Grafo<Vendedor> actualizarListaTodos(Vendedor usuario);

    public Grafo<Vendedor> actualizarListaSugeridos(Vendedor usuario);

    public void agregarContacto(Vendedor usuario, Vendedor nuevoContacto);

    public Grafo<Vendedor> actualizarListaContactos(Vendedor usuario);

    public ArbolBinario<Publicacion> actualizarMuro(Vendedor usuario);
}
