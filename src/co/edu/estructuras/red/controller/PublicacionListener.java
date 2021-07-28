package co.edu.estructuras.red.controller;

import co.edu.estructuras.red.model.Publicacion;
import co.edu.estructuras.red.model.Vendedor;

public interface PublicacionListener {
    public boolean darMeGusta(Publicacion publicacion);

    public void comentar(Publicacion publicacion);
}
