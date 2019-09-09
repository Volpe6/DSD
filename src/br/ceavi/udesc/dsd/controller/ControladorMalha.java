package br.ceavi.udesc.dsd.controller;

import br.ceavi.udesc.dsd.Main;
import br.ceavi.udesc.dsd.model.Direcao;
import br.ceavi.udesc.dsd.model.Malha;
import br.ceavi.udesc.dsd.model.Nodo;
import br.ceavi.udesc.dsd.model.Veiculo;
import br.ceavi.udesc.dsd.view.ObservadorDesenho;
import br.ceavi.udesc.dsd.view.ObservadorMalha;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class ControladorMalha implements ObservadoMalha, ObservadoDesenho, ObservadorEstadoVeiculo, Runnable {

    private String malha;
    private Malha modelMalha;
    private List<ObservadorMalha> obsMalha;
    private List<ObservadorDesenho> obsDesenho;
    public static final int TEMPO_CRIACAO_VEICULO = 10;
    
    public ControladorMalha(){
        this.obsMalha = new ArrayList<>();
        this.obsDesenho = new ArrayList<>();
        this.modelMalha = new Malha();
        this.modelMalha.addObservadorEstadoVeiculo(this);
    }
    
    public void adicionaObsMalha(ObservadorMalha obs){
        this.obsMalha.add(obs);
    }
    
    public void adicionaObsDesenho(ObservadorDesenho obs){
        this.obsDesenho.add(obs);
    }

    @Override
    public void notificaMalhaCarregada() {
        this.obsMalha.forEach((obs) -> {
            obs.malhaCarregada();
        });
    }

    @Override
    public void notificaVeiculoCriado(ControladorVeiculo veiculo) {
        this.obsMalha.forEach((obs) -> {
            obs.veiculoCriado(veiculo);
        });
    }

    @Override
    public void notificaVeiculoRemovido(String id) {
        this.obsMalha.forEach((obs) -> {
            obs.veiculoRemovido(id);
        });
    }

    @Override
    public void notificaDesenhoAlterado() {
        this.obsDesenho.forEach((obs) -> {
            obs.desenhoAlterado();
        });
    }
    
    public void carregaMalha() throws FileNotFoundException {
        this.carregaMalha(null);
    }

    public void carregaMalha(File arquivoMalha) throws FileNotFoundException {
        this.recarregaMalha();
        Nodo nodoAtual = null;
        for (int i = 0; i < Main.TAMANHO_MALHA_TESTE; i++) {
            Nodo nodo = this.modelMalha.criaNodo(nodoAtual == null || i == Main.TAMANHO_MALHA_TESTE - 1);
            if(nodoAtual != null){
                nodo.addNodoAdjacente(Direcao.OESTE, nodoAtual);
            }
            nodoAtual = nodo;
            nodo.setPosX(i);
            nodo.setPosY(5);
            if(i == 4 || i == 9){
                nodo.setCruzamento(true);
                Nodo nodoVAtual = nodo;
                for (int j = 0; j < 5; j++) {
                    Nodo nodoV = this.modelMalha.criaNodo(j == 0);
                    nodoV.addNodoAdjacente(Direcao.NORTE, nodoVAtual);
                    nodoVAtual = nodoV;
                    nodoV.setPosX(i);
                    nodoV.setPosY(j);
                }
                nodoVAtual = nodo;
                for (int j = 6; j < Main.TAMANHO_MALHA_TESTE; j++) {
                    Nodo nodoV = this.modelMalha.criaNodo(j == Main.TAMANHO_MALHA_TESTE - 1);
                    nodoV.addNodoAdjacente(Direcao.NORTE, nodoVAtual);
                    nodoVAtual = nodoV;
                    nodoV.setPosX(i);
                    nodoV.setPosY(j);
                }
            }
        }
        this.notificaMalhaCarregada();
        this.notificaDesenhoAlterado();
    }

    public void criaVeiculo() {
        Veiculo veiculo = this.modelMalha.criaVeiculo();
        if(veiculo != null){
            this.notificaVeiculoCriado(this.iniciaControlador(veiculo));
        }
    }
    
    private ControladorVeiculo iniciaControlador(Veiculo veiculo){
        ControladorVeiculo controlador = new ControladorVeiculo(veiculo);
        controlador.addObservador(this);
        Thread thread = new Thread(controlador);
        thread.start();
        return controlador;
    }

    public void finalizaSimulacao() {
        Main.getInstance().setRodando(false);
        this.modelMalha.limpaVeiculos();
    }

    public String[] getAllNodos() {
        String[] nodos = new String[this.modelMalha.getTamanho()];
        int i = 0;
        for (Map.Entry<String, Nodo> entry : this.modelMalha.getNodos().entrySet()) {
            String key = entry.getKey();
            nodos[i] = key;
            i++;
        }
        return nodos;
    }

    public Point getPosicaoNodo(String id) {
        Nodo nodo = this.modelMalha.getNodo(id);
        if(nodo == null){
            return new Point(0, 0);
        }
        return new Point(nodo.getPosX(), nodo.getPosY());
    }
    
    public Map<Direcao, ControladorVeiculo> getControladoresVeiculos(String id){
        Nodo nodo = this.modelMalha.getNodo(id);
        if(nodo == null){
            return new HashMap<>();
        }
        Map<Direcao, ControladorVeiculo> controladores = new HashMap<>();
        for (Map.Entry<Direcao, Veiculo> entry : nodo.getVeiculoPresente().entrySet()) {
            Direcao key = entry.getKey();
            Veiculo value = entry.getValue();
            controladores.put(key, new ControladorVeiculo(value));
        }
        return controladores;
    }

    public void iniciaSimulacao() {
        if(Main.getInstance().isRodando()){
            return;
        }
        if(this.modelMalha.getTamanho() == 0){
            return;
        }
        Main.getInstance().setRodando(true);
        Thread threadExecucao = new Thread(this);
        threadExecucao.start();
    }

    public void recarregaMalha() {
        this.finalizaSimulacao();
        this.modelMalha.recriaNodos();
    }

    @Override
    public void run() {
        while(Main.getInstance().isRodando()){
            if(this.modelMalha.getTotalVeiculos() < this.modelMalha.getConfiguracao().getQtdVeiculos()){
                this.criaVeiculo();
            }
            this.notificaDesenhoAlterado();
            try {
                Thread.sleep(Main.TEMPO_ATUALIZACAO);
            } catch (InterruptedException ex) {}
        }
    }

    @Override
    public void veiculoRemovido(Veiculo veiculo) {
        this.modelMalha.removeVeiculo(veiculo);
        this.notificaVeiculoRemovido(veiculo.getId());
    }
}
