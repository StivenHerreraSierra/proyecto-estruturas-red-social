package estructuras;

import java.util.ArrayList;

import estructuras.exception.EnlaceException;
import estructuras.exception.NodoException;

/**
 * @author Stiven Herrera Sierra.
 * Representa un nodo o vértice del grafo.
*/
public class Nodo<T> {
	private ArrayList<Enlace<T>> listaEnlaces;
	private T valor;
	
	public Nodo(T valor) {
		this.valor = valor;
		this.listaEnlaces = new ArrayList<>();
	}
	
	public int getGrado() {
		return this.listaEnlaces.size();
	}
	
	public boolean estaConectadoCon(Nodo<T> nodo) {
		return listaEnlaces.stream().anyMatch(enlace -> enlace.getNodoDestino().equals(nodo));
	}
	
	public void conectar(Nodo<T> nodo) throws NodoException {
		if(estaConectadoCon(nodo))
			throw new NodoException("Error conectando los nodos: el nodo ya está conectado.");
		
		conectar(nodo, 1);
	}
	
	public void conectar(Nodo<T> nodo, int peso) throws NodoException {
		if(estaConectadoCon(nodo))
			throw new NodoException("Error conectando los nodos: el nodo ya está conectado.");
		
		listaEnlaces.add(new Enlace<T>(nodo, peso));
	}
	
	public void desconectar(Nodo<T> nodo) throws NodoException {
		if(!estaConectadoCon(nodo))
			throw new NodoException("Error desconectando los nodos: el nodo no está conectado");
		
		listaEnlaces.removeIf(enlace -> enlace.getNodoDestino().equals(nodo));
	}
	
	public void desconectar(Enlace<T> enlace) throws EnlaceException {
		if(!listaEnlaces.contains(enlace))
			throw new EnlaceException("Error removiendo enlace: el enlace no existe en la lista.");
		
		listaEnlaces.remove(enlace);
	}

	public ArrayList<Enlace<T>> getListaEnlaces() {
		return listaEnlaces;
	}

	public void setListaEnlaces(ArrayList<Enlace<T>> listaEnlaces) {
		this.listaEnlaces = listaEnlaces;
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}
	
	public Enlace<T> getEnlace(Nodo<T> nodoDestino) throws EnlaceException {
		if(!estaConectadoCon(nodoDestino))
			throw new EnlaceException("Error obteniendo enlace: no hay un enlace al nodo indicado.");
		
		return listaEnlaces.get(getIndiceEnlace(nodoDestino));
	}
	
	public int getIndiceEnlace(Nodo<T> nodoDestino) {
		int indice = -1;
		Enlace<T> enlaceAux;
		
		for (int i = 0; i < listaEnlaces.size() && indice == -1; i++) {
			enlaceAux = listaEnlaces.get(i);
			if(enlaceAux.getNodoDestino().equals(nodoDestino))
				indice = i;
		}
		
		return indice;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object == null || getClass() != object.getClass())
			return false;
		if(object == this)
			return true;
		
		@SuppressWarnings("unchecked")
		Nodo<T> nodo = (Nodo<T>) object;
		return nodo.valor.equals(valor);
	}
	
	public void limpiarListaEnlaces() {
		this.listaEnlaces.clear();
	}

	@Override
	public String toString() {
		return "Nodo [valor=" + valor + "]";
	}
}
