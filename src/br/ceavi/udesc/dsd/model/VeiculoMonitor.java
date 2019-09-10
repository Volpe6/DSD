package br.ceavi.udesc.dsd.model;

import java.awt.Point;

/**
 *
 * @author Jeferson Penz
 */
public class VeiculoMonitor extends Veiculo{
    
    public VeiculoMonitor(Nodo nodo) {
        super(nodo);
    }

    @Override
    public synchronized String getId() {
        return super.getId();
    }

    @Override
    public synchronized Point getPosicao() {
        return super.getPosicao();
    }

    @Override
    public synchronized Direcao getDirecao() {
        return super.getDirecao();
    }

    @Override
    public synchronized String toString() {
        return super.toString();
    }

    @Override
    public synchronized Nodo getNodoAtual() {
        return super.getNodoAtual();
    }
    
}
