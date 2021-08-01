package co.edu.estructuras.red.model;

import co.edu.estructuras.red.estructuras.lista.ListaDoble;
import co.edu.estructuras.red.model.exception.ChatException;

public class Chat {
    private Vendedor usuario1;
    private Vendedor usuario2;
    private ListaDoble<String> mensajes;

    public Chat(Vendedor usuario1, Vendedor usuario2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.mensajes = new ListaDoble<>();
    }

    public boolean estanChateando(Vendedor usuario) {
        return usuario.equals(usuario1) || usuario.equals(usuario2);
    }

    public void agregarMensaje(String mensaje) throws ChatException {
        if(mensaje.isEmpty())
            throw new ChatException("Error agregando mensaje: el mensaje enviado no puede estar vacío.");
        mensajes.agregarfinal(mensaje);
    }

    public Vendedor getUsuario1() {
        return usuario1;
    }

    public Vendedor getUsuario2() {
        return usuario2;
    }

    public ListaDoble<String> getMensajes() {
        return mensajes;
    }

    public String mensajesToString() {
        String mensajesAux = "";

        for(String mensaje : mensajes) {
            mensajesAux += mensaje + "\n\n";
        }

        return mensajesAux;
    }
}
