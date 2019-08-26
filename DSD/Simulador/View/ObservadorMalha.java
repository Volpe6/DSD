package View;

import Model.Veiculo;

/**
 * @author jefip
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public interface ObservadorMalha {

	public void malhaCarregada();

	/**
	 * 
	 * @param veiculo
	 */
	public void veiculoCriado(Veiculo veiculo);

	/**
	 * 
	 * @param id
	 */
	public void veiculoRemovido(String id);

}