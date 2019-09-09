package br.ceavi.udesc.dsd.model;

import java.util.Map;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class NodoMonitor extends Nodo {

    public NodoMonitor() {
        super();
    }

    @Override
    public synchronized Map<Direcao, Veiculo> getVeiculoPresente() {
        return super.getVeiculoPresente();
    }

    @Override
    public synchronized Map<Direcao, Nodo> getNodoAdjacentes() {
        return super.getNodoAdjacentes();
    }

    @Override
    public synchronized void limpaVeiculos() {
        super.limpaVeiculos();
    }

    @Override
    public synchronized void limpaNodosAdjacentes() {
        super.limpaNodosAdjacentes();
    }
}
