package br.ceavi.udesc.dsd;

import br.ceavi.udesc.dsd.view.TelaPrincipal;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 */
public class Main {

    public static final int TEMPO_ATUALIZACAO = 33; // 30 Atualizações por segundo
    public static final String TITULO_APP = "Malha Viária"; // 30 Atualizações por segundo
    
    private static Main instance;
    private boolean rodando;
    private TelaPrincipal tela;

    private Main() {
        this.rodando = false;
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
}
