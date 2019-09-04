package br.ceavi.udesc.dsd.controller;

import br.ceavi.udesc.dsd.Main;
import br.ceavi.udesc.dsd.model.Malha;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class ControladorMalha implements Runnable {

    private String malha;
    private Malha modelMalha;

    public ControladorMalha() {

    }

    public void carregaMalha(File arquivoMalha) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivoMalha));
        Optional<String> resultadoLeitura = reader.lines().reduce((String res, String linha) -> {
            res += "\n" + linha;
            return res;
        });
        if(resultadoLeitura.isPresent()){
            this.malha = resultadoLeitura.get();
            System.out.println(this.malha);
        }
        else {
            throw new FileNotFoundException();
        }
    }

    public void criaVeiculo() {
        // Tem que verificar se tem uma posição válida para o veículo.
        // Tem que criar o veiculo, colocar em um nodo.
        // Tem que colocar no array de veículos.
    }

    public void finalizaSimulacao() {
        Main.getInstance().setRodando(false);
    }

    public String[] getAllNodos() {
        return null;
    }

    public void getNodo(String id) {

    }

    public void iniciaSimulacao() {
        if(Main.getInstance().isRodando()){
            return;
        }
        Main.getInstance().setRodando(true);
        Thread threadExecucao = new Thread(this);
        threadExecucao.start();
    }

    public void recarregaMalha() {

    }

    @Override
    public void run() {
        while(Main.getInstance().isRodando()){
            if(this.modelMalha.getTotalVeiculos() < this.modelMalha.getConfiguracao().getQtdVeiculos()){
                this.criaVeiculo();
            }
            try {
                Thread.sleep(Main.TEMPO_ATUALIZACAO);
            } catch (InterruptedException ex) {}
        }
    }
}
