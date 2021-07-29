package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.model.Vendedor;

public interface PublicarListener {
    public boolean publicar(Vendedor usuario, String nombre, String categoria, String precio);
}
