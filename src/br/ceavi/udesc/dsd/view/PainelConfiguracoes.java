package br.ceavi.udesc.dsd.view;

import br.ceavi.udesc.dsd.controller.ControladorConfiguracoes;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class PainelConfiguracoes extends JPanel implements ObservadorConfiguracoes {

    private JButton botaoSalvar;
    private ControladorConfiguracoes controlador;

    public PainelConfiguracoes() {

    }

    @Override
    public void configuracaoAlterada() {

    }
}
