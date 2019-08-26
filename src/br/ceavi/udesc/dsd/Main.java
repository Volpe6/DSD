package br.ceavi.udesc.dsd;

import br.ceavi.udesc.dsd.view.TelaPrincipal;

/**
 * 
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 */
public class Main {

    private static Main instance;
    private boolean rodando;
    private TelaPrincipal tela;

    private void Main() {
        this.rodando = false;
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

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     *
     * @param newVal
     */
    public void setRodando(boolean newVal) {
        rodando = newVal;
    }
}//end Main
