package br.ceavi.udesc.dsd.view;

import br.ceavi.udesc.dsd.Main;
import br.ceavi.udesc.dsd.controller.ControladorMalha;
import br.ceavi.udesc.dsd.controller.ControladorVeiculo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:31 PM
 */
public class PainelMalha extends JPanel implements ObservadorMalha, ObservadorDesenho {

    private ControladorMalha controlador;
    public Map<String, DesenhoVeiculo> veiculos;
    public List<DesenhoNodo> nodos;

    public PainelMalha() {
        super();
        this.iniciaPropriedades();
        this.iniciaConfiguracoesPainel();
    }
    
    private void iniciaPropriedades(){
        this.veiculos = new HashMap<>();
        this.nodos = new ArrayList<>();
        this.controlador = new ControladorMalha();
        this.controlador.adicionaObsDesenho(this);
        this.controlador.adicionaObsMalha(this);
    }
    
    private void iniciaConfiguracoesPainel(){
        this.setBackground(Color.WHITE);
    }

    public void finalizaSimulacao() {
        this.controlador.finalizaSimulacao();
        this.desenhoAlterado();
    }

    public void iniciaSimulacao() {
        this.controlador.iniciaSimulacao();
    }

    public void recriaMalha(File arquivoMalha) throws FileNotFoundException {
        this.finalizaSimulacao();
        this.controlador.carregaMalha(arquivoMalha);
    }

    public void recriaMalha() throws FileNotFoundException {
        this.finalizaSimulacao();
        this.controlador.carregaMalha();
    }

    @Override
    public void malhaCarregada() {
        this.nodos = new ArrayList<>();
        for (String nodo : this.controlador.getAllNodos()) {
            DesenhoNodo desenho = new DesenhoNodo(this.controlador, nodo);
            this.nodos.add(desenho);
        }
    }

    @Override
    public void veiculoCriado(ControladorVeiculo veiculo) {
        SwingUtilities.invokeLater(() -> {
            this.veiculos.put(veiculo.getIdVeiculo(), new DesenhoVeiculo(veiculo));
        });
    }

    @Override
    public void veiculoRemovido(String id) {
        SwingUtilities.invokeLater(() -> {
            this.veiculos.remove(id);
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.getWidth() > this.getHeight()){
            Main.getInstance().setTamanhoBlocoGrid(this.getHeight() / /*controlador.getQtdLinhasMalha()*/25);
        }
        else {
            Main.getInstance().setTamanhoBlocoGrid((this.getWidth() + 10)/ /*controlador.getQtdColunasMalha()*/25);
        }
        this.nodos.forEach((nodo) -> {
            nodo.desenha((Graphics2D) g);
        });
        this.veiculos.entrySet().stream().map((entry) -> {
            String key = entry.getKey();
            return entry;
        }).forEachOrdered((entry) -> {
            DesenhoVeiculo veiculo = entry.getValue();
            veiculo.desenha((Graphics2D) g);
        });
    }

    @Override
    public void desenhoAlterado() {
        SwingUtilities.invokeLater(() -> {
            this.repaint();
        });
    }
}
