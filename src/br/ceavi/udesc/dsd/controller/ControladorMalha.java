package br.ceavi.udesc.dsd.controller;

import br.ceavi.udesc.dsd.Main;
import br.ceavi.udesc.dsd.model.Direcao;
import br.ceavi.udesc.dsd.model.Malha;
import br.ceavi.udesc.dsd.model.Nodo;
import br.ceavi.udesc.dsd.model.Veiculo;
import br.ceavi.udesc.dsd.view.ObservadorDesenho;
import br.ceavi.udesc.dsd.view.ObservadorMalha;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    private int[][] malha;
    private Malha modelMalha;
    private List<ObservadorMalha> obsMalha;
    private List<ObservadorDesenho> obsDesenho;
    public static final int TEMPO_CRIACAO_VEICULO = 10;

    private String[][] matrizTemp;

    public ControladorMalha() {
        this.obsMalha = new ArrayList<>();
        this.obsDesenho = new ArrayList<>();
        this.modelMalha = new Malha();
        this.modelMalha.addObservadorEstadoVeiculo(this);
    }

    public void adicionaObsMalha(ObservadorMalha obs) {
        this.obsMalha.add(obs);
    }

    public void adicionaObsDesenho(ObservadorDesenho obs) {
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

    public void carregaMalha() throws FileNotFoundException, IOException {
        this.carregaMalha(null);
    }

    public int getQtdLinhasMalha() {
        return malha.length;
    }

    public int getQtdColunasMalha() {
        return malha[0].length;
    }

    private void lerArquivo(File arquivo) throws IOException {
        int[][] mat;
        if (arquivo == null) {
            mat = this.malha;
            if(mat == null){
                return;
            }
        } else {
            BufferedReader bf;
            bf = new BufferedReader(new FileReader(arquivo));

            String n1 = bf.readLine();
            String n2 = bf.readLine();

            if (n1.contains("\t")) {
                n1 = n1.replace("\t", "");
            }

            if (n2.contains("\t")) {
                n2 = n2.replace("\t", "");
            }

            int linha = Integer.parseInt(n1);
            int coluna = Integer.parseInt(n2);

            mat = new int[linha][coluna];

            int cont = 0;
            while (bf.ready()) {
                String[] sLinha = bf.readLine().split("\t");
                for (int i = 0; i < sLinha.length; i++) {
                    mat[cont][i] = Integer.parseInt(sLinha[i]);
                }
                cont++;
            }
        }

        this.malha = mat;
        this.matrizTemp = new String[mat.length][mat[0].length];

        for (int linha = 0; linha < mat.length; linha++) {
            for (int coluna = 0; coluna < mat[linha].length; coluna++) {
                Nodo nodoAtual = this.criaNodoLinhaColuna(mat, linha, coluna);
                if (nodoAtual != null) {
                    nodoAtual.setPosX(coluna);
                    nodoAtual.setPosY(linha);
                    nodoAtual.setNumero(mat[linha][coluna]);
                    matrizTemp[linha][coluna] = nodoAtual.getId();
                }
                else {
                    matrizTemp[linha][coluna] = "0";
                }
            }
        }
    }
        
    public Nodo criaNodoLinhaColuna(int[][] mat, int linha, int coluna){
        Nodo nodoAtual     = null;
        int val = mat[linha][coluna];
        if (val > 0) {
            Nodo proximoNodo1;
            Nodo proximoNodo2;
            switch (val) {
                case 1:
                    nodoAtual = this.modelMalha.criaNodo((linha == mat.length - 1 || linha == 0));
                    if (linha != 0) {
                        proximoNodo1 = modelMalha.getNodo(matrizTemp[linha - 1][coluna]);
                        if (proximoNodo1 != null) {
                            nodoAtual.addNodoAdjacente(Direcao.NORTE, proximoNodo1);
                        }
                    }
                    break;
                case 2:
                    nodoAtual = this.modelMalha.criaNodo((coluna == mat.length - 1 || coluna == 0));
                    break;
                case 3:
                    nodoAtual = this.modelMalha.criaNodo((linha == mat.length - 1 || linha == 0));
                    break;
                case 4:
                    nodoAtual = this.modelMalha.criaNodo((coluna == mat[0].length - 1 || coluna == 0));
                    if (coluna != 0) {// se for zero é borda
                        proximoNodo1 = modelMalha.getNodo(matrizTemp[linha][coluna - 1]);
                        if (proximoNodo1 != null) {
                            nodoAtual.addNodoAdjacente(Direcao.OESTE, proximoNodo1);
                        }
                    }
                    break;
                case 5:
                    nodoAtual = this.modelMalha.criaNodo(false);
                    proximoNodo1 = modelMalha.getNodo(matrizTemp[linha - 1][coluna]);
                    if (proximoNodo1 != null) {
                        nodoAtual.addNodoAdjacente(Direcao.NORTE, proximoNodo1);
                    }
                    nodoAtual.setCruzamento(true);
                    break;
                case 6:
                    nodoAtual = this.modelMalha.criaNodo(false);
                    nodoAtual.setCruzamento(true);
                    break;
                case 7:
                    nodoAtual = this.modelMalha.criaNodo(false);
                    nodoAtual.setCruzamento(true);
                    break;
                case 8:
                    nodoAtual = this.modelMalha.criaNodo(false);
                    proximoNodo1 = modelMalha.getNodo(matrizTemp[linha][coluna - 1]);
                    if (proximoNodo1 != null) {
                        nodoAtual.addNodoAdjacente(Direcao.OESTE, proximoNodo1);
                    }
                    nodoAtual.setCruzamento(true);
                    break;
                case 9://Cruzamento Cima e Direita
                    nodoAtual = this.modelMalha.criaNodo(false);

                    proximoNodo1 = modelMalha.getNodo(matrizTemp[linha - 1][coluna]);
                    if (proximoNodo1 != null) {
                        nodoAtual.addNodoAdjacente(Direcao.NORTE, proximoNodo1);
                    }
                    
                    nodoAtual.setCruzamento(true);
                    break;
                case 10://Cruzamento Cima e Esquerda
                    nodoAtual = this.modelMalha.criaNodo(false);
                    proximoNodo1 = modelMalha.getNodo(matrizTemp[linha - 1][coluna]);
                    if (proximoNodo1 != null) {
                        nodoAtual.addNodoAdjacente(Direcao.NORTE, proximoNodo1);
                    }

                    proximoNodo2 = modelMalha.getNodo(matrizTemp[linha][coluna - 1]);
                    if (proximoNodo2 != null) {
                        nodoAtual.addNodoAdjacente(Direcao.OESTE, proximoNodo2);
                    }
                    nodoAtual.setCruzamento(true);
                    break;
                case 11:
                    nodoAtual = this.modelMalha.criaNodo(false);
                    nodoAtual.setCruzamento(true);
                    break;
                case 12://Cruzamento Baixo e Esquerda
                    nodoAtual = this.modelMalha.criaNodo(false);
                    
                    proximoNodo1 = modelMalha.getNodo(matrizTemp[linha][coluna - 1]);
                    if (proximoNodo1 != null) {
                        nodoAtual.addNodoAdjacente(Direcao.OESTE, proximoNodo1);
                    }
                    
                    nodoAtual.setCruzamento(true);
                    break;
            }
            if(linha > 0){
                Nodo nodoAnterior  = modelMalha.getNodo(matrizTemp[linha - 1][coluna]);
                if(nodoAnterior != null && isNodoDesce(nodoAnterior.getNumero())){
                    nodoAnterior.addNodoAdjacente(Direcao.SUL, nodoAtual);
                }
            }
            if(coluna > 0){
                Nodo nodoAnterior2 = modelMalha.getNodo(matrizTemp[linha][coluna - 1]);
                if(nodoAnterior2 != null && isNodoDireita(nodoAnterior2.getNumero())){
                    nodoAnterior2.addNodoAdjacente(Direcao.LESTE, nodoAtual);
                }
            }
        }
        return nodoAtual;
    }
    
    public boolean isNodoDesce(int valor){
        return valor == 3 || valor == 7 || valor == 11 || valor == 12;
    }
    
    public boolean isNodoDireita(int valor){
        return valor == 2 || valor == 6 || valor == 9 || valor == 11;
    }

    public void carregaMalha(File arquivoMalha) throws FileNotFoundException, IOException {
        this.recarregaMalha();

        lerArquivo(arquivoMalha);

        this.notificaMalhaCarregada();
        this.notificaDesenhoAlterado();
    }

    public Nodo getNodo(String id) {
        return modelMalha.getNodo(id);
    }

    public void criaVeiculo() {
        Veiculo veiculo = this.modelMalha.criaVeiculo();
        if (veiculo != null) {
            this.notificaVeiculoCriado(this.iniciaControlador(veiculo));
        }
    }

    private ControladorVeiculo iniciaControlador(Veiculo veiculo) {
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
        if (nodo == null) {
            return new Point(0, 0);
        }
        return new Point(nodo.getPosX(), nodo.getPosY());
    }

    public Map<Direcao, ControladorVeiculo> getControladoresVeiculos(String id) {
        Nodo nodo = this.modelMalha.getNodo(id);
        if (nodo == null) {
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
        if (Main.getInstance().isRodando()) {
            return;
        }
        if (this.modelMalha.getTamanho() == 0) {
            return;
        }
        this.modelMalha.limpaVeiculos();
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
        while (Main.getInstance().isRodando()) {
            if (this.modelMalha.getTotalVeiculos() < this.modelMalha.getConfiguracao().getQtdVeiculos()) {
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
