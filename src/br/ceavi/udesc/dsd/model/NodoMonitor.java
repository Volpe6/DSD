package br.ceavi.udesc.dsd.model;


/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class NodoMonitor extends Nodo {

	public NodoMonitor(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
}//end NodoMonitor