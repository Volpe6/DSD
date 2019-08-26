package br.ceavi.udesc.dsd.model;


/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class Malha implements ObservadoMalha, ObservadoDesenho {

	public Configuracoes configuracao;
	public Nodo nodos;

	public Malha(){

	}

	public void finalize() throws Throwable {

	}
	public void criaVeiculo(){

	}

	/**
	 * 
	 * @param malha
	 */
	public void recriaNodos(String malha){

	}

	public void notificaMalhaCarregada(){

	}

	/**
	 * 
	 * @param veiculo
	 */
	public void notificaVeiculoCriado(Veiculo veiculo){

	}

	/**
	 * 
	 * @param id
	 */
	public void notificaVeiculoRemovido(String id){

	}

	public void notificaDesenhoAlterado(){

	}
}//end Malha