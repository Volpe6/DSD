package br.ceavi.udesc.dsd.view;

import javax.swing.JFrame;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:31 PM
 */
public class TelaConfiguracoes extends JFrame {

    public PainelConfiguracoes configuracao;

    public TelaConfiguracoes(TelaPrincipal telaPrincipal) {
        this.iniciaConfiguracoes();
        this.iniciaComponentes(telaPrincipal);
    }
    
    private void iniciaConfiguracoes(){
        this.setResizable(false);
        this.setSize(500, 400);
    }

    private void iniciaComponentes(TelaPrincipal telaPrincipal) {
        this.configuracao = new PainelConfiguracoes(telaPrincipal);
        this.add(this.configuracao);
    }

}
