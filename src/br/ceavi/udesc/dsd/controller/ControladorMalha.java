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
import java.util.ArrayList;
import java.util.Arrays;
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
    
    
    private String [][] matrizTemp;
    
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
    
    public int getQtdLinhasMalha() {
        return malha.length;
    }
    
    public int getQtdColunasMalha() {
        return malha[0].length;
    }

    private void lerArquivo(File arquivo) {
        if(arquivo == null) {
            return;
        }
        try {
            BufferedReader bf = new BufferedReader(new FileReader(arquivo));

            String n1 = bf.readLine();
            String n2 = bf.readLine();
            
            if(n1.contains("\t")) {
                n1 = n1.replace("\t", "");
            }
            
            if(n2.contains("\t")) {
                n2 = n2.replace("\t", "");
            }
            
            int linha  = Integer.parseInt(n1);
            int coluna = Integer.parseInt(n2);

            int[][] mat = new int [linha][coluna];
            
            int cont = 0;
            while(bf.ready()) {
                String[] sLinha = bf.readLine().split("\t");
                for(int i = 0; i < sLinha.length; i++) {
                    mat[cont][i] = Integer.parseInt(sLinha[i]);
                }
                cont++;
            }
            
            malha = mat;
            
            matrizTemp = new String[mat.length][mat[0].length];// contem os id
              
//            int[][] matrizTemp = new int[mat.length][mat[0].length];// contem os id


            for(int k = 0; k<mat.length; k++) {
                for(int l = 0; l < mat[0].length; l++) {
                    System.out.print(mat[k][l] + "\t");
                }
                System.out.println("");
            }
            
//            int iasasa = mat[13][19];

            Nodo nodoAtual = null;
            for(int i = 0; i < mat.length; i++ ) {
                for(int j = 0; j < mat[0].length; j++) {
                    if(mat[i][j] > 0) {
                        
//                        if(i == 15 && j == 12) {
//                            int asas = 0;
//                        }
                        
                        switch(mat[i][j]) {
                            case 1:
                                 nodoAtual = this.modelMalha.criaNodo((i == mat.length - 1 || i == 0));
                                 if(i != 0) {
                                     Nodo nodoAnterior = modelMalha.getNodo(matrizTemp[i-1][j]);
                                     if(nodoAnterior != null) {
                                        nodoAtual.addNodoAdjacente(Direcao.NORTE, nodoAnterior);
                                     }
                                 }
                                 nodoAtual.setPosX(i);
                                 nodoAtual.setPosY(j);
                                 nodoAtual.setNumero(mat[i][j]);
                                 matrizTemp[i][j] = nodoAtual.getId();
                                break;
                            case 2:
                                 nodoAtual = this.modelMalha.criaNodo((j == mat[0].length - 1 || j == 0));
                                 if(j != 0) {// se for zero é borda
                                     Nodo nodoAnterior = modelMalha.getNodo(matrizTemp[i][j-1]);
                                     if(nodoAnterior != null) {
                                        nodoAnterior.addNodoAdjacente(Direcao.LESTE, nodoAtual);
                                        //nodoAtual.addNodoAdjacente(Direcao.SUL, nodoAnterior);
                                     }
                                 }
                                 nodoAtual.setPosX(i);
                                 nodoAtual.setPosY(j);
                                 nodoAtual.setNumero(mat[i][j]);
                                 matrizTemp[i][j] = nodoAtual.getId();
                                break;
                            case 3:
                                 nodoAtual = this.modelMalha.criaNodo((i == mat.length - 1 || i == 0));
                                 if(i != 0) {// se for zero é borda
                                     Nodo nodoAnterior = modelMalha.getNodo(matrizTemp[i-1][j]);
                                     if(nodoAnterior != null) {
                                        nodoAnterior.addNodoAdjacente(Direcao.SUL, nodoAtual);
                                        //nodoAtual.addNodoAdjacente(Direcao.SUL, nodoAnterior);
                                     }
                                 }
                                 nodoAtual.setPosX(i);
                                 nodoAtual.setPosY(j);
                                 nodoAtual.setNumero(mat[i][j]);
                                 matrizTemp[i][j] = nodoAtual.getId();
                                break;
                            case 4:
                                 nodoAtual = this.modelMalha.criaNodo((j == mat[0].length - 1 || j == 0));
                                 if(j != 0) {// se for zero é borda
                                     Nodo nodoAnterior = modelMalha.getNodo(matrizTemp[i][j-1]);
                                     if(nodoAnterior != null) {
                                        nodoAtual.addNodoAdjacente(Direcao.OESTE, nodoAnterior);
                                     }
                                 }
                                 nodoAtual.setPosX(i);
                                 nodoAtual.setPosY(j);
                                 nodoAtual.setNumero(mat[i][j]);
                                 matrizTemp[i][j] = nodoAtual.getId();
                                break;
                            case 5:
                                nodoAtual = this.modelMalha.criaNodo((i == mat.length - 1 || i == 0));
                                 if(i != 0) {
                                     Nodo nodoAnterior = modelMalha.getNodo(matrizTemp[i-1][j]);
                                     if(nodoAnterior != null) {
                                        nodoAtual.addNodoAdjacente(Direcao.NORTE, nodoAnterior);
                                     }
                                 }
                                 nodoAtual.setPosX(i);
                                 nodoAtual.setPosY(j);
                                 nodoAtual.setNumero(mat[i][j]);
                                 nodoAtual.setCruzamento(true);
                                 matrizTemp[i][j] = nodoAtual.getId();
                                break;
                            case 6:
                                nodoAtual = this.modelMalha.criaNodo((j == mat[0].length - 1 || j == 0));
                                 if(j != 0) {// se for zero é borda
                                     Nodo nodoAnterior = modelMalha.getNodo(matrizTemp[i][j-1]);
                                     if(nodoAnterior != null) {
                                        nodoAnterior.addNodoAdjacente(Direcao.LESTE, nodoAtual);
                                        //nodoAtual.addNodoAdjacente(Direcao.SUL, nodoAnterior);
                                     }
                                 }
                                 nodoAtual.setPosX(i);
                                 nodoAtual.setPosY(j);
                                 nodoAtual.setNumero(mat[i][j]);
                                 nodoAtual.setCruzamento(true);
                                 matrizTemp[i][j] = nodoAtual.getId();
                                break;
                            case 7:
                                ///////////
                                nodoAtual = this.modelMalha.criaNodo((i == mat.length - 1 || i == 0));
                                 if(i != 0) {// se for zero é borda
                                     Nodo nodoAnterior = modelMalha.getNodo(matrizTemp[i-1][j]);
                                     if(nodoAnterior != null) {
                                        nodoAnterior.addNodoAdjacente(Direcao.SUL, nodoAtual);
                                        //nodoAtual.addNodoAdjacente(Direcao.SUL, nodoAnterior);
                                     }
                                 }
                                 nodoAtual.setPosX(i);
                                 nodoAtual.setPosY(j);
                                 nodoAtual.setNumero(mat[i][j]);
                                 nodoAtual.setCruzamento(true);
                                 matrizTemp[i][j] = nodoAtual.getId();
                                break;
                            case 8:
                                nodoAtual = this.modelMalha.criaNodo((j == mat[0].length - 1 || j == 0));
                                 if(j != 0) {// se for zero é borda
                                     Nodo nodoAnterior = modelMalha.getNodo(matrizTemp[i][j-1]);
                                     if(nodoAnterior != null) {
                                        nodoAtual.addNodoAdjacente(Direcao.OESTE, nodoAnterior);
                                     }
                                 }
                                 nodoAtual.setPosX(i);
                                 nodoAtual.setPosY(j);
                                 nodoAtual.setNumero(mat[i][j]);
                                 nodoAtual.setCruzamento(true);
                                 matrizTemp[i][j] = nodoAtual.getId();
                                break;
                            case 9://Cruzamento Cima e Direita
                                 nodoAtual = this.modelMalha.criaNodo((i == mat.length - 1 || i == 0));
                                 
                                 if(i != 0) {// se for zero é borda
                                     Nodo nodoAcima = modelMalha.getNodo(matrizTemp[i-1][j]);
                                     if(nodoAcima != null) {
                                        nodoAtual.addNodoAdjacente(Direcao.NORTE, nodoAcima);
                                     }
                                      
                                     Nodo nodoEsquerdo = modelMalha.getNodo(matrizTemp[i][j-1]);
                                     if(nodoEsquerdo != null) {
                                        nodoEsquerdo.addNodoAdjacente(Direcao.LESTE, nodoAtual);
                                        //nodoAtual.addNodoAdjacente(Direcao.SUL, nodoAnterior);
                                     }
                                 }
                                 
                                 
                                 nodoAtual.setPosX(i);
                                 nodoAtual.setPosY(j);
                                 nodoAtual.setNumero(mat[i][j]);
                                 nodoAtual.setCruzamento(true);
                                 matrizTemp[i][j] = nodoAtual.getId();             
                                 break;
                            case 10://Cruzamento Cima e Esquerda
                                  nodoAtual = this.modelMalha.criaNodo((i == mat.length - 1 || i == 0));
                                 
                                 if(i != 0) {// se for zero é borda
                                     Nodo nodoCima = modelMalha.getNodo(matrizTemp[i-1][j]);
                                     if(nodoCima != null) {
                                        nodoAtual.addNodoAdjacente(Direcao.NORTE, nodoCima);
                                     }
                                      
                                     Nodo nodoAnterior = modelMalha.getNodo(matrizTemp[i][j-1]);
                                     if(nodoAnterior != null) {
                                        nodoAtual.addNodoAdjacente(Direcao.OESTE, nodoAnterior);
                                     }
                                 }
                                 
                                 
                                 nodoAtual.setPosX(i);
                                 nodoAtual.setPosY(j);
                                 nodoAtual.setNumero(mat[i][j]);
                                 nodoAtual.setCruzamento(true);
                                 matrizTemp[i][j] = nodoAtual.getId();
                                break;
                            case 11://Cruzamento Direita e Baixo
                                 nodoAtual = this.modelMalha.criaNodo((i == mat.length - 1 || i == 0));
                                 
                                 if(i != 0) {// se for zero é borda
                                     Nodo nodoCima = modelMalha.getNodo(matrizTemp[i-1][j]);
                                     if(nodoCima != null) {
                                        nodoCima.addNodoAdjacente(Direcao.SUL, nodoAtual);
                                        //nodoAtual.addNodoAdjacente(Direcao.SUL, nodoAnterior);
                                     }
                                     
                                     
                                     Nodo nodoEsquerdo = modelMalha.getNodo(matrizTemp[i][j-1]);
                                     if(nodoEsquerdo != null) {
                                        nodoEsquerdo.addNodoAdjacente(Direcao.LESTE, nodoAtual);
                                        //nodoAtual.addNodoAdjacente(Direcao.SUL, nodoAnterior);
                                     }
                                 }
                                 
                                 
                                 nodoAtual.setPosX(i);
                                 nodoAtual.setPosY(j);
                                 nodoAtual.setNumero(mat[i][j]);
                                 nodoAtual.setCruzamento(true);
                                 matrizTemp[i][j] = nodoAtual.getId();
                                break;
                            case 12://Cruzamento Baixo e Esquerda
                                 nodoAtual = this.modelMalha.criaNodo((i == mat.length - 1 || i == 0));
                                 
                                 if(i != 0) {// se for zero é borda
                                     Nodo nodoCima = modelMalha.getNodo(matrizTemp[i-1][j]);
                                     if(nodoCima != null) {
                                        nodoCima.addNodoAdjacente(Direcao.SUL, nodoAtual);
                                        //nodoAtual.addNodoAdjacente(Direcao.SUL, nodoAnterior);
                                     }
                                     
                                     Nodo nodoEsquerdo = modelMalha.getNodo(matrizTemp[i][j-1]);
                                     if(nodoEsquerdo != null) {
                                        nodoAtual.addNodoAdjacente(Direcao.OESTE, nodoEsquerdo);
                                     }
                                 }
                                 
                                 nodoAtual.setPosX(i);
                                 nodoAtual.setPosY(j);
                                 nodoAtual.setNumero(mat[i][j]);
                                 nodoAtual.setCruzamento(true);
                                 matrizTemp[i][j] = nodoAtual.getId();
                                break;
                        }
                        
                    } else {
                        System.out.print("0" + "\t");
                        matrizTemp[i][j] = "0";
                        
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void carregaMalha(File arquivoMalha) throws FileNotFoundException {
        this.recarregaMalha();
            
        lerArquivo(arquivoMalha);
        
        this.notificaMalhaCarregada();
        this.notificaDesenhoAlterado();

       
//        int[][] matrizTemp = new int[mat.length][mat[0].length];// contem os id
//        
//        Nodo nodoAtual = null;
//        for(int i = 0; i < mat.length; i++ ) {
//            for(int k = 0; k < mat[0].length; k++) {
//                
//            }
//        }
//        
//        
//        Nodo nodoAtual = null;
//        for (int i = 0; i < Main.TAMANHO_MALHA_TESTE; i++) {
//            Nodo nodo = this.modelMalha.criaNodo(nodoAtual == null || i == Main.TAMANHO_MALHA_TESTE - 1);
//            if(nodoAtual != null){
//                nodo.addNodoAdjacente(Direcao.OESTE, nodoAtual);
//            }
//            nodoAtual = nodo;
//            nodo.setPosX(i);
//            nodo.setPosY(5);
//            if(i == 4 || i == 9){
//                nodo.setCruzamento(true);
//                Nodo nodoVAtual = nodo;
//                for (int j = 0; j < 5; j++) {
//                    Nodo nodoV = this.modelMalha.criaNodo(j == 0);
//                    nodoV.addNodoAdjacente(Direcao.NORTE, nodoVAtual);
//                    nodoVAtual = nodoV;
//                    nodoV.setPosX(i);
//                    nodoV.setPosY(j);
//                }
//                nodoVAtual = nodo;
//                for (int j = 6; j < Main.TAMANHO_MALHA_TESTE; j++) {
//                    Nodo nodoV = this.modelMalha.criaNodo(j == Main.TAMANHO_MALHA_TESTE - 1);
//                    nodoV.addNodoAdjacente(Direcao.NORTE, nodoVAtual);
//                    nodoVAtual = nodoV;
//                    nodoV.setPosX(i);
//                    nodoV.setPosY(j);
//                }
//            }
//        }
//        this.notificaMalhaCarregada();
//        this.notificaDesenhoAlterado();
    }

    public Nodo getNodo(String id) {
        return modelMalha.getNodo(id);
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
