package Model;


/**
 * @author jefip
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