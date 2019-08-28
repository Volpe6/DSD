package br.ceavi.udesc.dsd.view;

import br.ceavi.udesc.dsd.model.Veiculo;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public interface ObservadorMalha {

    public void malhaCarregada();

    public void veiculoCriado(Veiculo veiculo);

    public void veiculoRemovido(String id);

}
