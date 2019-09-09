package br.ceavi.udesc.dsd.controller;

import br.ceavi.udesc.dsd.model.Veiculo;

/**
 *
 * @author Jeferson Penz
 */
public interface ObservadorEstadoVeiculo {
    
    public void veiculoRemovido(Veiculo veiculo);
    
}
