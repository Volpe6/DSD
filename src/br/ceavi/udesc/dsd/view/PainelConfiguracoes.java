package br.ceavi.udesc.dsd.view;

import br.ceavi.udesc.dsd.controller.ControladorConfiguracoes;
import br.ceavi.udesc.dsd.model.TipoMonitoracao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class PainelConfiguracoes extends JPanel implements ObservadorConfiguracoes {

    private JButton botaoSalvar;
    private JTextField numeroVeiculos;
    private JComboBox<TipoMonitoracao> tipoMonitoracao;
    private ControladorConfiguracoes controlador;

    public PainelConfiguracoes(TelaPrincipal telaPrincipal) {
        super();
        this.controlador = new ControladorConfiguracoes();
        this.controlador.adicionaObservador(this);
        this.controlador.adicionaObservador(telaPrincipal);
        this.setBackground(new Color(225, 225, 225));
        this.iniciaComponentes();
    }

    @Override
    public void configuracaoAlterada() {
        this.numeroVeiculos.setText(Integer.toString(this.controlador.getQtdVeiculos()));
        this.tipoMonitoracao.setSelectedItem(this.controlador.getTipoMonitoracao());
    }
    
    private void iniciaComponentes(){
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        Font fonteLabel = new Font("Arial", Font.BOLD, 23);
        Font fonteCampo = new Font("Arial", Font.PLAIN, 23);
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.insets = new Insets(5, 15, 5, 15);
        gbcLabel.anchor = GridBagConstraints.PAGE_START;
        GridBagConstraints gbcCampo = new GridBagConstraints();
        gbcCampo.gridx = 1;
        gbcCampo.insets = new Insets(5, 15, 5, 10);
        gbcCampo.anchor = GridBagConstraints.PAGE_START;
        JLabel labelVeiculos = new JLabel("Quantidade de Veículos: ");
        labelVeiculos.setFont(fonteLabel);
        this.add(labelVeiculos, gbcLabel);
        this.numeroVeiculos = new JTextField("20");
        this.numeroVeiculos.setFont(fonteCampo);
        this.numeroVeiculos.setPreferredSize(new Dimension(100, 30));
        this.add(this.numeroVeiculos, gbcCampo);
        JLabel labelTipoMonitoracao = new JLabel("Tipo de Monitoração: ");
        labelTipoMonitoracao.setFont(fonteLabel);
        this.add(labelTipoMonitoracao, gbcLabel);
        this.tipoMonitoracao = new JComboBox<>(new TipoMonitoracao[] { TipoMonitoracao.MONITOR, TipoMonitoracao.SEMAFORO});
        this.tipoMonitoracao.setFont(fonteCampo);
        this.add(this.tipoMonitoracao, gbcCampo);
        
        GridBagConstraints gbcBotao = new GridBagConstraints();
        gbcBotao.gridx = 0;
        gbcBotao.gridwidth = 2;
        gbcBotao.insets = new Insets(25, 15, 25, 15);
        gbcBotao.anchor = GridBagConstraints.PAGE_START;
        this.botaoSalvar = new JButton("Salvar");
        this.botaoSalvar.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.botaoSalvar.setPreferredSize(new Dimension(175, 75));
        this.botaoSalvar.addActionListener((ActionEvent e) -> {
            int valorQtdVeiculos = 10;
            try {
                valorQtdVeiculos = Math.max(Integer.parseInt(numeroVeiculos.getText()), 10);
            } catch (NumberFormatException ex){}
            controlador.salvaConfiguracoes(valorQtdVeiculos, (TipoMonitoracao) tipoMonitoracao.getSelectedItem());
        });
        this.add(this.botaoSalvar, gbcBotao);
    }
}
