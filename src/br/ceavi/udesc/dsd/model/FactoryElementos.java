package br.ceavi.udesc.dsd.model;


/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class FactoryElementos {

	public FactoryElementos(){

	}

	public void finalize() throws Throwable {

	}
	public Nodo criaNodoMonitor(){
		return null;
	}

	public Nodo criaNodoSemaforo(){
		return null;
	}
}//end FactoryElementos