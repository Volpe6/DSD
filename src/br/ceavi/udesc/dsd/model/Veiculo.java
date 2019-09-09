package br.ceavi.udesc.dsd.model;

import br.ceavi.udesc.dsd.Main;
import java.awt.Point;
import java.util.Set;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:31 PM
 */
public abstract class Veiculo {

    private String id;
    private Nodo nodoAtual;
    private Direcao direcao;
    private int tempoMovimento;

    public Veiculo(Nodo nodo) {
        this.id = "Veiculo " + Main.getInstance().getNewIdVeiculo();
        this.direcao = nodo.getDirecaoInicial();
        nodo.adicionaVeiculo(direcao, this);
        this.nodoAtual = nodo;
        this.tempoMovimento = (int)(Math.random() * 75) + 25;
    }

    public Point getPosicao() {
        return new Point(this.nodoAtual.getPosX(), this.nodoAtual.getPosY());
    }
    
    public boolean moveVeiculo() throws InterruptedException{
        Nodo nodoCruzamento = null;
        Nodo proximoNodo = this.getNodoAtual().getProximoNodo(this.getDirecao());
        Direcao proximaDirecao = this.getDirecao();
        if(proximoNodo == null){
            this.getNodoAtual().removeVeiculo(getDirecao());
            return false;
        }
        if(proximoNodo.isCruzamento()){
            nodoCruzamento = proximoNodo;
            proximaDirecao = this.getDirecaoAleatoria(nodoCruzamento.getDirecoesDisponiveis());
            proximoNodo = nodoCruzamento.getProximoNodo(proximaDirecao);
        }
        boolean aguardando = true;
        while(aguardando){
            Thread.sleep(this.getTempoProximoMovimento());
            aguardando = false;
            if(nodoCruzamento != null){
                if(nodoCruzamento.isVeiculoPresente(proximaDirecao)){
                    aguardando = true;
                }
            }
            if(proximoNodo.isVeiculoPresente(proximaDirecao)){
                aguardando = true;
            }
        }
        if(nodoCruzamento != null){
            doRealizaMovimento(nodoCruzamento, proximaDirecao);
        }
        doRealizaMovimento(proximoNodo, proximaDirecao);
        return true;
    }
    
    private void doRealizaMovimento(Nodo nodoAlvo, Direcao novaDirecao) throws InterruptedException{
        this.nodoAtual.removeVeiculo(this.getDirecao());
        this.nodoAtual = nodoAlvo;
        this.setDirecao(novaDirecao);
        this.nodoAtual.adicionaVeiculo(this.getDirecao(), this);
        Thread.sleep(this.getTempoProximoMovimento());
    }
    
    private int getTempoProximoMovimento(){
        return this.tempoMovimento + (int)(Math.random() * 25);
    }
    
    public Direcao getDirecaoAleatoria(Set<Direcao> direcoesDisp){
        Direcao novaDirecao = null;
        while (novaDirecao == null){
            novaDirecao = direcoesDisp.toArray(new Direcao[] {})[(int) (Math.random() * (float) direcoesDisp.size())];
            if(novaDirecao == this.direcao.getDirecaoOposta()){
                novaDirecao = null;
            }
        }
        return novaDirecao;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }
    
    public String getId() {
        return id;
    }

    public Nodo getNodoAtual() {
        return nodoAtual;
    }

    @Override
    public String toString() {
        if(this.nodoAtual == null){
            return this.id + " desacoplado";
        }
        return this.id + " em " + this.nodoAtual.getPosX() + ", " + this.nodoAtual.getPosY();
    }
}
