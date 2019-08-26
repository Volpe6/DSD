package br.ceavi.udesc.dsd.view;

import br.ceavi.udesc.dsd.controller.ControladorMalha;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:31 PM
 */
public class PainelMalha extends JPanel implements ObservadorConfiguracoes, ObservadorMalha, ObservadorDesenho {

	private ControladorMalha controlador;
	public DesenhoVeiculo veiculos;
	public DesenhoNodo nodos;

	public PainelMalha(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	public void finalizaSimulacao(){

	}

	public void iniciaSimulacao(){

	}

	public void paintComponent(){

	}

	/**
	 * 
	 * @param malha
	 */
	public void recriaMalha(String malha){

	}

	public void configuracaoAlterada(){

	}

	public void malhaCarregada(){

	}

	/**
	 * 
	 * @param veiculo
	 */
	public void veiculoCriado(Veiculo veiculo){

	}

	/**
	 * 
	 * @param id
	 */
	public void veiculoRemovido(String id){

	}

	public void desenhoAlterado(){

	}
}//end PainelMalha