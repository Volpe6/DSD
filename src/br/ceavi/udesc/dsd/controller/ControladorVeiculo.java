package br.ceavi.udesc.dsd.controller;

import br.ceavi.udesc.dsd.model.Veiculo;
import java.awt.Point;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class ControladorVeiculo implements Runnable {

    private Veiculo veiculo;

    public ControladorVeiculo() {
        this.veiculo = new Veiculo();
    }

    public Point getPosicaoVeiculo() {
        return this.veiculo.getPosicao();
    }

    public void moveVeiculo() {

    }

    @Override
    public void run() {

    }
}
