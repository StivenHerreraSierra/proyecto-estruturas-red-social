package co.edu.estructuras.red.estructuras.arbol;

/**
 * Clase que representa un Nodo del �rbol binario
 * 
 *
 * @param <T>
 */
public class Nodo<T extends Comparable<T>> {

	private Nodo<T> izquierdo, derecho;
	private Nodo<T> padre;
	private T elemento;
	
	/**
	 * Constructor de la clase
	 * @param elemento Dato del nodo
	 */
	public Nodo(T elemento) {
		this.elemento = elemento;
	}
	
	public Nodo(T elemento, Nodo<T> padre) {
		this.elemento = elemento;
		this.padre = padre;
	}
	
	/**
	 * Agrega un nuevo elemento en el �rbol
	 * @param nuevo Nuevo dato
	 * @return true si lo pudo guardar
	 */
	public boolean agregar(T nuevo) 
	{
		if( nuevo.compareTo( elemento ) < 0 ) 
		{
			if(izquierdo==null) {
				izquierdo = new Nodo<>(nuevo, this);
				return true;
			}else {
				return izquierdo.agregar(nuevo);
			}
		}
		else 
		{
			if( nuevo.compareTo( elemento ) > 0 ) 
			{
				if(derecho==null) {
					derecho = new Nodo<>(nuevo, this);
					return true;
				}else {
					return derecho.agregar(nuevo);
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Determina si un Nodo es una Hoja
	 * @return true si es Hoja
	 */
	public boolean esHoja() {
		return izquierdo == null && derecho == null;
	}

	/**
	 * 
	 * @return
	 */
	public boolean tieneUnHijo() {
		return (izquierdo!=null && derecho==null) || (derecho!=null && izquierdo==null );
	}
	
	/**
	 * @return the izq
	 */
	public Nodo<T> getIzquierdo() {
		return izquierdo;
	}

	/**
	 * @param izq the izq to set
	 */
	public void setIzquierdo(Nodo<T> izq) {
		this.izquierdo = izq;
	}

	/**
	 * @return the der
	 */
	public Nodo<T> getDerecho() {
		return derecho;
	}

	/**
	 * @param der the der to set
	 */
	public void setDerecho(Nodo<T> der) {
		this.derecho = der;
	}

	/**
	 * @return the elemento
	 */
	public T getElemento() {
		return elemento;
	}

	/**
	 * @param elemento the elemento to set
	 */
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	/**
	 * @return the padre
	 */
	public Nodo<T> getPadre() {
		return padre;
	}

	/**
	 * @param padre the padre to set
	 */
	public void setPadre(Nodo<T> padre) {
		this.padre = padre;
	}
	
}
