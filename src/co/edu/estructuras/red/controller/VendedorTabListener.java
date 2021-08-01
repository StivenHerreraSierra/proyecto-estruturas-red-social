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

    public void agregarContactoBuscado(Vendedor usuario, String nombre);

    public boolean darMeGusta(Vendedor usuario, Publicacion publicacion);

    public void comentar(Vendedor usuario, Publicacion publicacion, String nuevoComentario);

    boolean meGusta(Publicacion publicacion, Vendedor usuario);

    public void mostrarComentarios(Publicacion publicacion, Vendedor usuario);

    void iniciarChat(Vendedor usuario, Vendedor newValue);
}
