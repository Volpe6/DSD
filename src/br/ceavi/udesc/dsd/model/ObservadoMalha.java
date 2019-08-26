package br.ceavi.udesc.dsd.model;


/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public interface ObservadoMalha {

	public void notificaMalhaCarregada();

	/**
	 * 
	 * @param veiculo
	 */
	public void notificaVeiculoCriado(Veiculo veiculo);

	/**
	 * 
	 * @param id
	 */
	public void notificaVeiculoRemovido(String id);

}