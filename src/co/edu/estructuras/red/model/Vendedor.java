package co.edu.estructuras.red.model;

import co.edu.estructuras.red.estructuras.arbol.ArbolBinario;
import co.edu.estructuras.red.estructuras.lista.ListaDoble;
import co.edu.estructuras.red.model.exception.ChatException;
import co.edu.estructuras.red.model.exception.PublicacionException;
import co.edu.estructuras.red.model.exception.VendedorException;

import java.io.Serializable;
import java.util.Iterator;

public class Vendedor implements Serializable {
    private String nombreVendedor;
    private ArbolBinario<Producto> productos;
    private ArbolBinario<Publicacion> publicaciones;
    private ListaDoble<Chat> chats;

    public Vendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
        this.productos = new ArbolBinario<>();
        this.publicaciones = new ArbolBinario<>();
        this.chats = new ListaDoble<>();
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public ArbolBinario<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public ArbolBinario<Producto> getProductos() {
        return productos;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;
        if(object == this)
            return true;

        @SuppressWarnings("unchecked")
        Vendedor vendedor = (Vendedor) object;
        return vendedor.nombreVendedor.equals(this.nombreVendedor);
    }

    @Override
    public String toString() {
        return nombreVendedor;
    }

    public void agregarPublicacion(String nombre, String categoria, double precio) throws VendedorException, PublicacionException {
        if(nombre.isEmpty() || categoria.isEmpty())
            throw new PublicacionException("Error publicando producto: el nombre o categoria no puede estar vacío");

        Producto producto = new Producto(nombre, categoria, precio);

        if(!productos.agregar(producto))
            throw new VendedorException("Error agregando producto: el producto ya se ha registrado.");

        Publicacion publicacion = new Publicacion(producto, this);

        if(!publicaciones.agregar(publicacion))
            throw new VendedorException("Error agregando publicación: la publicación ya se ha registrado.");
    }

    public void comentarPublicacion(String comentario, Publicacion publicacion, Vendedor usuario) throws PublicacionException {
        publicacion.agregarComentario(comentario, usuario);
    }

    public ListaDoble<Chat> getChats() {
        return chats;
    }

    public void agregarMensaje(String mensaje, Vendedor usuario2) throws ChatException {
        Chat chat = getChatCon(usuario2);

        if(chat == null) {
            chat = new Chat(this, usuario2);
            chats.agregarfinal(chat);
            usuario2.agregarChat(chat);
        }

        chat.agregarMensaje(mensaje);
    }

    private void agregarChat(Chat chat) {
        chats.agregarfinal(chat);
    }

    public Chat getChatCon(Vendedor usuario2) {
        Chat chatEncontrado = null;
        Chat chatAux;
        Iterator<Chat> it = chats.iterator();

        while(it.hasNext() && chatEncontrado == null) {
            chatAux = it.next();
            if(chatAux.estanChateando(usuario2))
                chatEncontrado = chatAux;
        }

        return chatEncontrado;
    }

    public String getMensajesChatString(Vendedor usuario2) {
        Chat chat = getChatCon(usuario2);

        if(chat == null)
            return "";

        return chat.mensajesToString();
    }

    public int getCantidadMensajesCon(Vendedor usuario2) {
        int contador = 0;
        Iterator<Chat> it = chats.iterator();
        Chat chatAux;

        while(it.hasNext() && contador == 0) {
            chatAux = it.next();
            if(chatAux.estanChateando(usuario2))
                contador = chatAux.getMensajes().getTamanio();
        }

        return contador;
    }
}
