package br.ceavi.udesc.dsd.controller;

import br.ceavi.udesc.dsd.Main;
import br.ceavi.udesc.dsd.model.Direcao;
import br.ceavi.udesc.dsd.model.Veiculo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class ControladorVeiculo implements ObservadoEstadoVeiculo, Runnable {

    private Veiculo veiculo;
    private List<ObservadorEstadoVeiculo> obs;

    public ControladorVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.obs = new ArrayList<>();
    }

    public Point getPosicaoVeiculo() {
        return this.veiculo.getPosicao();
    }
    
    public Direcao getDirecaoVeiculo(){
        return this.veiculo.getDirecao();
    }

    @Override
    public void run() {
        boolean movendo = true;
        while(Main.getInstance().isRodando() && movendo){
            try {
                movendo = this.veiculo.moveVeiculo();
            } catch (InterruptedException ex) {}
        }
        this.notificaoVeiculoRemovido(veiculo);
    }
    
    public void addObservador(ObservadorEstadoVeiculo obs){
        this.obs.add(obs);
    }

    @Override
    public void notificaoVeiculoRemovido(Veiculo veiculo) {
        this.obs.forEach((ob) -> {
            ob.veiculoRemovido(veiculo);
        });
    }

    public String getIdVeiculo() {
        return this.veiculo.getId();
    }
}
