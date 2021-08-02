package co.edu.estructuras.red.estructuras.grafo;

import java.io.Serializable;

/**
 * @author Stiven Herrera Sierra.
 * Representa la arista que conecta 2 vértices.
 */
public class Enlace<T> implements Serializable {
	private Nodo<T> nodoDestino;
	private int peso;
	
	public Enlace(Nodo<T> nodoDestino, int peso) {
		this.nodoDestino = nodoDestino;
		this.peso = peso;
	}

	public Nodo<T> getNodoDestino() {
		return nodoDestino;
	}

	public void setNodoDestino(Nodo<T> nodoDestino) {
		this.nodoDestino = nodoDestino;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Enlace [nodoDestino=" + nodoDestino + ", peso=" + peso + "]";
	}
}
