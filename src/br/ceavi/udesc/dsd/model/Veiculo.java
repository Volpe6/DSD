package br.ceavi.udesc.dsd.model;

import br.ceavi.udesc.dsd.view.ObservadorDesenho;
import java.awt.Point;
import java.util.List;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:31 PM
 */
public class Veiculo implements ObservadoDesenho {

    private String id;
    private Nodo nodoAtual;
    private List<ObservadorDesenho> observadores;

    public Veiculo() {

    }
    
    public void addObservador(ObservadorDesenho obs){
        this.observadores.add(obs);
    }

    @Override
    public void notificaDesenhoAlterado() {
        this.observadores.forEach((obs) -> {
            obs.desenhoAlterado();
        });
    }

    public Point getPosicao() {
        return null;
    }
}
