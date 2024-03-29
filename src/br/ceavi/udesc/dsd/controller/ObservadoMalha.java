package br.ceavi.udesc.dsd.controller;

import br.ceavi.udesc.dsd.model.Veiculo;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public interface ObservadoMalha {

    public void notificaMalhaCarregada();

    public void notificaVeiculoCriado(ControladorVeiculo veiculo);

    public void notificaVeiculoRemovido(String id);

}
