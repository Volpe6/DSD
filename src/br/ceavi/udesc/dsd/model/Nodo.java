package br.ceavi.udesc.dsd.model;

import br.ceavi.udesc.dsd.view.ObservadorDesenho;
import java.util.List;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public abstract class Nodo implements ObservadoDesenho {
    
    private List<ObservadorDesenho> obsDesenho;

    private String id;
    private int posX;
    private int posY;
    private Veiculo veiculoPresente;
    private Nodo nodoAdjacentes;

    public Nodo() {

    }

    public List<ObservadorDesenho> getObsDesenho() {
        return obsDesenho;
    }

    public void setObsDesenho(List<ObservadorDesenho> obsDesenho) {
        this.obsDesenho = obsDesenho;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Veiculo getVeiculoPresente() {
        return veiculoPresente;
    }

    public void setVeiculoPresente(Veiculo veiculoPresente) {
        this.veiculoPresente = veiculoPresente;
    }

    public Nodo getNodoAdjacentes() {
        return nodoAdjacentes;
    }

    public void setNodoAdjacentes(Nodo nodoAdjacentes) {
        this.nodoAdjacentes = nodoAdjacentes;
    }
    
    public void adicionaObsDesenho(ObservadorDesenho obs){
        this.obsDesenho.add(obs);
    }
    
    @Override
    public void notificaDesenhoAlterado() {
        this.obsDesenho.forEach((obs) -> {
            obs.desenhoAlterado();
        });
    }
}
