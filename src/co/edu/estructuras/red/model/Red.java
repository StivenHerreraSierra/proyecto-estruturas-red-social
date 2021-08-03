package co.edu.estructuras.red.model;

import co.edu.estructuras.red.estructuras.arbol.ArbolBinario;
import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.estructuras.exception.GrafoException;
import co.edu.estructuras.red.estructuras.exception.NodoException;
import co.edu.estructuras.red.model.exception.ChatException;
import co.edu.estructuras.red.model.exception.PublicacionException;
import co.edu.estructuras.red.model.exception.RedSocialException;
import co.edu.estructuras.red.model.exception.VendedorException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Stiven Herrera Sierra
 */
public class Red implements Serializable {
    private Grafo<Vendedor> vendedores;

    public Red () {
        this.vendedores = new Grafo<>();
    }

    public Vendedor registrarVendedor(String nombre) throws RedSocialException, GrafoException {
        if(nombre == null || nombre.isEmpty())
            throw new RedSocialException("Error registrando un vendedor: nombre no puede tener un valor vacio.");


        if(vendedores.size() > 9)
            throw new RedSocialException("Error registrando contacto: la red ya alcanzo los 10 usuarios.");
        Vendedor vendedor = new Vendedor(nombre);
        vendedores.agregarNodo(vendedor);

        return vendedor;
    }

    public Grafo<Vendedor> getListaVendedoresAgregar(Vendedor vendedor) throws GrafoException, NodoException {
        Grafo<Vendedor> grafoCopia = vendedores.copiarGrafo();

        grafoCopia.eliminarNodo(vendedor);
        grafoCopia.eliminarNodos(vendedores.getSubgrafo(vendedor));

        return grafoCopia;
    }

    public Grafo<Vendedor> getListaSugeridosAgregar(Vendedor vendedor) throws GrafoException, NodoException {
        Grafo<Vendedor> grafoContactos = vendedores.getSubgrafo(vendedor);
        Grafo<Vendedor> grafoContactosSiguiente;
        Grafo<Vendedor> grafoSugeridos = new Grafo<>();

        Iterator<Vendedor> itContacto = grafoContactos.iterator();
        Iterator<Vendedor> itContactoSiguiente;
        Vendedor auxContacto, auxSiguiente;

        while(itContacto.hasNext()) {
            auxContacto = itContacto.next();
            grafoContactosSiguiente = vendedores.getSubgrafo(auxContacto);
            itContactoSiguiente = grafoContactosSiguiente.iterator();
            while(itContactoSiguiente.hasNext()) {
                auxSiguiente = itContactoSiguiente.next();
                if(!grafoContactos.existeNodo(auxSiguiente) && !grafoSugeridos.existeNodo(auxSiguiente))
                    grafoSugeridos.agregarNodo(auxSiguiente);
            }
        }

        return grafoSugeridos;
    }

    public Grafo<Vendedor> getListaContactos(Vendedor vendedor) throws GrafoException, NodoException {
        Grafo<Vendedor> contactos = vendedores.getSubgrafo(vendedor);
        contactos.eliminarNodo(vendedor);

        return contactos;
    }

    public void agregarContacto(Vendedor usuario, Vendedor nuevoContacto) throws GrafoException, NodoException, RedSocialException {
        if(usuario == null || nuevoContacto == null)
            throw new RedSocialException("Error agregando contacto: el usuario o el nuevo contacto es null -> " + usuario + " - " + nuevoContacto);
        else if(usuario.equals(nuevoContacto))
            throw new RedSocialException("Error agregando contacto: el usuario no puede agregarse a sí mismo.");
        else if(vendedores.estanConectados(usuario, nuevoContacto))
            throw new RedSocialException("Error agregando contacto: los usuarios ya son amigos.");

        vendedores.conectarNodos(usuario, nuevoContacto);
    }

    @Override
    public String toString() {
        return "Red{" +
                "vendedores=" + vendedores.toString() +
                '}';
    }

    public void registrarPublicacion(Vendedor usuario, String nombre, String categoria, double precio) throws RedSocialException, VendedorException, PublicacionException {
        if(!vendedores.existeNodo(usuario))
            throw new RedSocialException("Error publicando producto: el usuario no está registrado -> " + usuario);

        usuario.agregarPublicacion(nombre, categoria, precio);
    }

    public ArbolBinario<Publicacion> getPublicacionesVendedor(Vendedor vendedor) throws GrafoException, NodoException {
        ArbolBinario<Publicacion> publicaciones = new ArbolBinario<>();

        generarArbolPublicaciones(publicaciones, vendedor.getPublicaciones());

        for(Vendedor contacto : getListaContactos(vendedor))
            generarArbolPublicaciones(publicaciones, contacto.getPublicaciones());

        return publicaciones;
    }

    private void generarArbolPublicaciones(ArbolBinario<Publicacion> arbol, ArbolBinario<Publicacion> publicaciones) {
        Iterator<Publicacion> it = publicaciones.iterator();
        while(it.hasNext())
            arbol.agregar(it.next());
    }

    public Vendedor getVendedor(String nombre) throws RedSocialException {
        Iterator<Vendedor> it = vendedores.iterator();
        Vendedor aux, encontrado;
        encontrado = null;

        while(it.hasNext() && encontrado == null) {
            aux = it.next();
            if(aux.getNombreVendedor().equals(nombre))
                encontrado = aux;
        }

        if(encontrado == null)
            throw new RedSocialException("Error buscando vendedor: el nombre indicado no está asociado con uno de los vendedores registrados.");

        return encontrado;
    }

    public boolean darMeGusta(Vendedor usuario, Publicacion publicacion) {
        return publicacion.darMeGusta(usuario);
    }

    public boolean meGusta(Publicacion publicacion, Vendedor usuario) {
        return publicacion.dioMeGusta(usuario);
    }

    public void comentar(String comentario, Publicacion publicacion, Vendedor usuario) throws PublicacionException, RedSocialException {
        Vendedor propietario = publicacion.getPropietario();

        if(!vendedores.existeNodo(propietario))
            throw new RedSocialException("Error comentando publicacion: el propietario de la publicacion no esta registrado.");

        propietario.comentarPublicacion(comentario, publicacion, usuario);
    }

    public int contarProductosFecha(LocalDate desde, LocalDate hasta) throws RedSocialException {
        int contador = 0;

        if(desde.isAfter(hasta))
            throw new RedSocialException("Error contando las publicaciones en una fecha determinada: la fecha 'Desde' " +
                    "ocurre después de la fecha 'Hasta'.");

        for (Vendedor vendedor : vendedores) {
            for(Publicacion publicacion : vendedor.getPublicaciones()) {
                if(publicacion.esPublicadaEntre(desde, hasta))
                    contador++;
            }
        }
        return contador;
    }

    public Grafo<Vendedor> getVendedores() {
        return vendedores;
    }

    public List<Publicacion> getTopPublicaciones() {
        ArrayList<Publicacion> publicaciones = new ArrayList<>();

        for(Vendedor vendedor : vendedores) {
            publicaciones.addAll(vendedor.getPublicaciones().getListaInorden());
        }

        return publicaciones.stream()
                .sorted((o1, o2) -> Integer.compare(o2.getCantidadMeGusta(), o1.getCantidadMeGusta()))
                .limit(10)
                .collect(Collectors.toList());
    }

    public void agregarMensajeChat(String mensaje, Vendedor usuario1, Vendedor usuario2) throws GrafoException, RedSocialException, ChatException {
        if(!vendedores.existeNodo(usuario1) || !vendedores.existeNodo(usuario2) || !vendedores.estanConectados(usuario1, usuario2))
            throw new RedSocialException("Error enviando mensaje: uno o ambos usuarios no están registrados o no son amigos.");

        usuario1.agregarMensaje(mensaje, usuario2);
    }

    public String getMensajesChatString(Vendedor usuario1, Vendedor usuario2) throws RedSocialException {
        if(!vendedores.existeNodo(usuario1) || !vendedores.existeNodo(usuario2))
            throw new RedSocialException("Error enviando mensaje: uno o ambos usuarios no están registrados.");

        return usuario1.getMensajesChatString(usuario2);
    }

    public int getCantidadMensajesIntercambiados(Vendedor usuario1, Vendedor usuario2) throws RedSocialException, GrafoException {
        if(!vendedores.existeNodo(usuario1) || !vendedores.existeNodo(usuario2) || !vendedores.estanConectados(usuario1, usuario2))
            throw new RedSocialException("Error obteniendo la cantidad de mensajes intercambiados: uno o ambos usuarios" +
                    " no estan registrados o no estan conectados.");

        return usuario1.getCantidadMensajesCon(usuario2);
    }
}
