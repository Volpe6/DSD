package br.ceavi.udesc.dsd;

import br.ceavi.udesc.dsd.model.Configuracoes;
import br.ceavi.udesc.dsd.view.TelaPrincipal;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 */
public class Main {

    public static final int TEMPO_ATUALIZACAO = 16; // 16 Atualizações por segundo
    public static final String TITULO_APP = "Malha Viária";
    public static final int TAMANHO_MALHA_TESTE = 20;
    public static final boolean DEPURANDO = true;
    
    private static Main instance;
    private boolean rodando;
    private TelaPrincipal tela;
    private int idNodoAtual = 0;
    private int idVeiculoAtual = 0;
    private int tamanhoBlocoGrid = 0;
    private Configuracoes configuracoes;

    private Main() {
        this.rodando = false;
        this.configuracoes = new Configuracoes();
    }

    private void inicia() {
        TelaPrincipal oTela = new TelaPrincipal();
        oTela.setVisible(true);
    }

    public static Main getInstance() {
        if(instance == null){
            instance = new Main();
        }
        return instance;
    }

    public TelaPrincipal getTela() {
        return tela;
    }

    public boolean isRodando() {
        return rodando;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {}
        Main.getInstance().inicia();
    }

    public void setRodando(boolean newVal) {
        rodando = newVal;
    }

    public int getTamanhoBlocoGrid() {
        return tamanhoBlocoGrid;
    }

    public void setTamanhoBlocoGrid(int tamanhoBlocoGrid) {
        this.tamanhoBlocoGrid = tamanhoBlocoGrid;
    }
    
    public int getNewIdVeiculo(){
        return ++this.idVeiculoAtual;
    }
    
    public int getNewIdNodo(){
        return ++this.idNodoAtual;
    }
    
    public Configuracoes getConfiguracoes(){
        return this.configuracoes;
    }
}
