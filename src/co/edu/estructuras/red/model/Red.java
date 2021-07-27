package co.edu.estructuras.red.model;

import co.edu.estructuras.red.estructuras.grafo.Grafo;
import co.edu.estructuras.red.estructuras.exception.GrafoException;
import co.edu.estructuras.red.estructuras.exception.NodoException;
import co.edu.estructuras.red.model.exception.RedSocialException;

import java.util.Iterator;

public class Red {
    private Grafo<Vendedor> vendedores;

    public Red () {
        this.vendedores = new Grafo<>();
    }

    public Vendedor registrarVendedor(String nombre) throws GrafoException, RedSocialException {
        if(nombre == null || nombre.isEmpty())
            throw new RedSocialException("Error registrando un vendedor: nombre no puede tener un valor vacío.");
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

        vendedores.conectarNodos(usuario, nuevoContacto);
    }

    @Override
    public String toString() {
        return "Red{" +
                "vendedores=" + vendedores.toString() +
                '}';
    }

    public void registrarPublicacion(Vendedor usuario, String nombre, String categoria) throws RedSocialException, NodoException {
        if(!vendedores.existeNodo(usuario))
            throw new RedSocialException("Error publicando producto: el usuario no está registrado -> " + usuario);

        usuario.agregarPublicacion(nombre, categoria);
    }
}
