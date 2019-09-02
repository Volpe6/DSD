package br.ceavi.udesc.dsd.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:31 PM
 */
public class TelaPrincipal extends JFrame {

    private JButton botaoConfiguracao;
    private JButton botaoFinalizar;
    private JButton botaoIniciar;
    private JButton botaoNovaMalha;
    public PainelMalha malha;
    public TelaConfiguracoes telaConfiguracao;

    public TelaPrincipal() {
        this.iniciaConfiguracoesTela();
        this.iniciaComponentesTela();
    }
    
    private void iniciaConfiguracoesTela(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
        this.getContentPane().setBackground(Color.WHITE);
    }
    
    private void iniciaComponentesTela(){
        JPanel painelConfiguracao = new JPanel();
        painelConfiguracao.setMinimumSize(new Dimension(0, 100));
        painelConfiguracao.setPreferredSize(new Dimension(-1, 100));
        painelConfiguracao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        this.botaoConfiguracao = new JButton("Configurações");
        this.botaoConfiguracao.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.botaoConfiguracao.setPreferredSize(new Dimension(175, 75));
        this.botaoIniciar      = new JButton("Iniciar");
        this.botaoIniciar.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.botaoIniciar.setPreferredSize(new Dimension(150, 75));
        this.botaoFinalizar    = new JButton("Finalizar");
        this.botaoFinalizar.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.botaoFinalizar.setPreferredSize(new Dimension(150, 75));
        this.botaoNovaMalha    = new JButton("Nova Malha");
        this.botaoNovaMalha.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.botaoNovaMalha.setPreferredSize(new Dimension(150, 75));
        painelConfiguracao.add(this.botaoConfiguracao);
        painelConfiguracao.add(this.botaoFinalizar);
        painelConfiguracao.add(this.botaoIniciar);
        painelConfiguracao.add(this.botaoNovaMalha);
        painelConfiguracao.setBackground(new Color(225, 225, 225));
        painelConfiguracao.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(240, 240, 240)));
        this.add(painelConfiguracao);
        
        this.malha = new PainelMalha();
        this.add(this.malha);
    }

    public void exibeTelaConfiguracao() {

    }
}