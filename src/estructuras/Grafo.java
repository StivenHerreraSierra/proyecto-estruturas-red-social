package estructuras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import estructuras.exception.GrafoException;
import estructuras.exception.NodoException;

/**
 * @author Stiven Herrera Sierra.
 * Representa el grafo dirigido.
*/
public class Grafo<T> {
	private HashMap<String, Nodo<T>> nodos;
	private Nodo<T> raiz;
	
	public Grafo() {
		this.raiz = null;
		this.nodos = new HashMap<>();
	}
	
	public void agregarNodo(T valor) throws GrafoException {
		if(nodos.containsKey(String.valueOf(valor)))
			throw new GrafoException("Error agregando el nodo: el nodo ya se encuentra en el grafo.");
		
		Nodo<T> nodo = new Nodo<>(valor);
		
		nodos.put(String.valueOf(nodo.getValor()), nodo);
	}
	
	public void eliminarNodo(T valor) throws GrafoException, NodoException {
		if(!nodos.containsKey(String.valueOf(valor)))
			throw new GrafoException("Error eliminando el nodo: el nodo no se encuentra en el grafo.");
		
		aislarNodo(valor);
		
		nodos.remove(String.valueOf(valor));
	}
	
	public void aislarNodo(T valor) throws GrafoException, NodoException {
		if(!nodos.containsKey(String.valueOf(valor)))
			throw new GrafoException("Error aislando el nodo: el nodo no se encuentra en el grafo.");
		
		Nodo<T> nodoEliminar = nodos.get(String.valueOf(valor));
		desconectarNodo(valor);
		
		nodoEliminar.limpiarListaEnlaces();
	}
	
	public void desconectarNodo(T valor) throws GrafoException, NodoException {
		if(!nodos.containsKey(String.valueOf(valor)))
			throw new GrafoException("Error desconectando el nodo: el nodo no se encuentra en el grafo.");
		
		Nodo<T> nodoEliminar = nodos.get(String.valueOf(valor));
		
		Iterator<Nodo<T>> itNodo = nodos.values().iterator();
		Nodo<T> auxNodo;
		while(itNodo.hasNext()) {
			auxNodo = itNodo.next();
			if(auxNodo.estaConectadoCon(nodoEliminar))
				auxNodo.desconectar(nodoEliminar);
		}
	}
	
	public void conectarNodos(T valor1, T valor2, int peso) throws GrafoException, NodoException {
		if(!nodos.containsKey(String.valueOf(valor1)) || !nodos.containsKey(String.valueOf(valor2)))
				throw new GrafoException("Error conectando nodos: uno o ambos vertices no están en el grafo.");
		
		Nodo<T> nodo1 = nodos.get(String.valueOf(valor1));
		Nodo<T> nodo2 = nodos.get(String.valueOf(valor2));
		
		nodo1.conectar(nodo2, peso);
		nodo2.conectar(nodo1, peso);
	}
	
	public void conectarNodos(T valor1, T valor2) throws GrafoException, NodoException {
		conectarNodos(valor1, valor2, 1);
	}
	
	public void desconectarNodos(T valor1, T valor2) throws GrafoException, NodoException {
		if(!nodos.containsKey(String.valueOf(valor1)) || !nodos.containsKey(String.valueOf(valor2)))
			throw new GrafoException("Error desconectando nodos: uno o ambos vertices no están en el grafo.");
		
		Nodo<T> nodo1 = nodos.get(String.valueOf(valor1));
		Nodo<T> nodo2 = nodos.get(String.valueOf(valor2));
		
		nodo1.desconectar(nodo2);
		nodo2.desconectar(nodo1);
	}
	
	public boolean estanConectados(T valor1, T valor2) throws GrafoException {
		if(!nodos.containsKey(String.valueOf(valor1)) || !nodos.containsKey(String.valueOf(valor2)))
			throw new GrafoException("Error comprobando conexión: uno o ambos vertices no están en el grafo.");
		
		Nodo<T> nodo1 = nodos.get(String.valueOf(valor1));
		Nodo<T> nodo2 = nodos.get(String.valueOf(valor2));
		
		return nodo1.estaConectadoCon(nodo2); 
	}

	public ArrayList<Enlace<T>> getEnlacesNodo(T valor) throws GrafoException {
		if(!nodos.containsKey(String.valueOf(valor)))
			throw new GrafoException("Error retornando los enlaces: el nodo no está en el grafo.");
		
		Nodo<T> nodo = nodos.get(String.valueOf(valor));
		return nodo.getListaEnlaces();
	}
	
	public void setRaiz(T valor) throws GrafoException {
		if(!nodos.containsKey(String.valueOf(valor)))
			throw new GrafoException("Error definiendo raíz: el nodo raíz no está en el grafo.");
		
		Nodo<T> nodo = nodos.get(String.valueOf(valor));
		raiz = nodo;
	}
	
	public void setValorNodo(T valorAnterior, T valorNuevo) throws GrafoException {
		if(!nodos.containsKey(String.valueOf(valorAnterior)))
			throw new GrafoException("Error reemplazando valor de un nodo: el nodo no está en el grafo.");
		if(nodos.containsKey(String.valueOf(valorNuevo)))
			throw new GrafoException("Error reemplazando valor de un nodo: el nodo ya está en el grafo.");
		
		Nodo<T> nodo = nodos.get(String.valueOf(valorAnterior));
		nodo.setValor(valorNuevo);
		
		nodos.remove(String.valueOf(valorAnterior));
		nodos.put(String.valueOf(valorNuevo), nodo);
	}
	
	public int size() {
		return nodos.size();
	}
	
	public int totalEnlacesNodo(T valor) throws GrafoException {
		if(!nodos.containsKey(String.valueOf(valor)))
			throw new GrafoException("Error obteniendo total de enlaces: el nodo no está en el grafo.");
		
		return nodos.get(String.valueOf(valor)).getListaEnlaces().size();
	}
	
	public boolean existeNodo(T valor) {
		return nodos.containsKey(String.valueOf(valor));
	}
	
	public HashMap<String, Nodo<T>> getNodos() {
		return nodos;
	}
	
	@Override
	public String toString() {
		String resumen = "Grafo [nodos=" + nodos + ", raiz=" + raiz + "]\n";
		
		Iterator<Nodo<T>> it = nodos.values().iterator();
		Nodo<T> aux;
		while(it.hasNext()) {
			aux = it.next();
			resumen += "Nodo " + aux.getValor() + " -> " + aux.getListaEnlaces() + "\n";
		}
		
		return resumen;
	}
}
