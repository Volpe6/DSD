package br.ceavi.udesc.dsd.model;

import br.ceavi.udesc.dsd.Main;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public abstract class Nodo {

    private String id;
    private int posX;
    private int posY;
    private Map<Direcao, Veiculo> veiculoPresente;
    private Map<Direcao, Nodo> nodoAdjacentes;
    private boolean borda;
    private boolean cruzamento;
    private Direcao direcaoInicial;

    public Nodo() {
        this.id = "Nodo " + Main.getInstance().getNewIdNodo();
        this.veiculoPresente = new HashMap<>();
        this.nodoAdjacentes = new HashMap<>();
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

    public boolean isVeiculoPresente(Direcao direcao) {
        if(this.isCruzamento()){
            return !getVeiculoPresente().isEmpty();
        }
        return getVeiculoPresente().containsKey(direcao);
    }

    public void adicionaVeiculo(Direcao direcao, Veiculo veiculo) {
        this.getVeiculoPresente().put(direcao, veiculo);
    }
    
    public void removeVeiculo(Direcao direcao){
        this.getVeiculoPresente().remove(direcao);
    }
    
    public void limpaVeiculos(){
        this.veiculoPresente = new HashMap<>();
    }
    
    public void limpaNodosAdjacentes(){
        this.nodoAdjacentes = new HashMap<>();
    }

    public void addNodoAdjacente(Direcao direcao, Nodo nodo) {
        this.getNodoAdjacentes().put(direcao, nodo);
//        if(!nodo.hasNodoAdjacente(this)){
//            nodo.addNodoAdjacente(direcao.getDirecaoOposta(), this);
//        }
    }

    public Map<Direcao, Veiculo> getVeiculoPresente() {
        return veiculoPresente;
    }
    
    public Set<Direcao> getDirecoesDisponiveis(){
        return this.getNodoAdjacentes().keySet();
    }
    
//    public boolean hasNodoAdjacente(Nodo nodo){
//        return this.getNodoAdjacentes().containsValue(nodo);
//    }
    
    public Nodo getProximoNodo(Direcao direcao){
        return this.getNodoAdjacentes().get(direcao);
    }
    
    public Map<Direcao, Nodo> getNodoAdjacentes(){
        return this.nodoAdjacentes;
    }
    
    public void setBorda(boolean borda){
        this.borda = borda;
    }

    public boolean isBorda() {
        return borda;
    }

    public boolean isCruzamento() {
        return cruzamento;
    }

    public void setCruzamento(boolean cruzamento) {
        this.cruzamento = cruzamento;
    }

    public Direcao getDirecaoInicial() {
        if(this.direcaoInicial == null){
            for (Direcao proxDirecao : this.getDirecoesDisponiveis()) {
                if(this.getProximoNodo(proxDirecao) != null){
                    this.direcaoInicial = proxDirecao;
                    break;
                }
            }
        }
        return this.direcaoInicial;
    }

    @Override
    public String toString() {
        return posX + ", " + posY;
    }
}
