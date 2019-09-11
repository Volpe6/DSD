package br.ceavi.udesc.dsd.model;

import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class NodoSemaforo extends Nodo {

    private Semaphore mutex;

    public NodoSemaforo() {
        mutex = new Semaphore(1);
    }

    @Override
    public Map<Direcao, Veiculo> getVeiculoPresente() {
        try {
            mutex.acquire();
            mutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.getVeiculoPresente();
    }

    @Override
    public Map<Direcao, Nodo> getNodoAdjacentes() {
        try {
            mutex.acquire();
            mutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return super.getNodoAdjacentes();
    }

    @Override
    public void limpaVeiculos() {
        try {
            mutex.acquire();
            mutex.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.limpaVeiculos();
    }

    @Override
    public void limpaNodosAdjacentes() {
        try {
            mutex.acquire();
            mutex.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.limpaNodosAdjacentes();
    }
}
