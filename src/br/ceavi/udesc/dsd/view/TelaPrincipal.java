package br.ceavi.udesc.dsd.view;

import br.ceavi.udesc.dsd.Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:31 PM
 */
public class TelaPrincipal extends JFrame implements ObservadorConfiguracoes{

    private JButton botaoConfiguracao;
    private JButton botaoFinalizar;
    private JButton botaoIniciar;
    private JButton botaoNovaMalha;
    public PainelMalha malha;
    public TelaConfiguracoes telaConfiguracao;

    public TelaPrincipal() {
        super(Main.TITULO_APP);
        this.iniciaConfiguracoesTela();
        this.iniciaComponentesTela();
        this.iniciaTelaConfiguracao();
    }
    
    private void iniciaConfiguracoesTela(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(575, 400));
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
        this.botaoIniciar.setPreferredSize(new Dimension(90, 75));
        this.botaoFinalizar    = new JButton("Finalizar");
        this.botaoFinalizar.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.botaoFinalizar.setPreferredSize(new Dimension(110, 75));
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
        
        JFileChooser escolheMalha = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de malha", "txt");
        escolheMalha.setFileFilter(filter);
        this.botaoConfiguracao.addActionListener((ActionEvent e) -> {
            exibeTelaConfiguracao();
        });
        this.botaoIniciar.addActionListener((ActionEvent e) -> {
            malha.iniciaSimulacao();
        });
        this.botaoFinalizar.addActionListener((ActionEvent e) -> {
            malha.finalizaSimulacao();
        });
        this.botaoNovaMalha.addActionListener((ActionEvent e) -> {
            doEscolheMalha(escolheMalha);
        });
        
        this.malha = new PainelMalha();
        this.add(this.malha);
    }
    
    private void iniciaTelaConfiguracao() {
        this.telaConfiguracao = new TelaConfiguracoes(this);
    }

    public void exibeTelaConfiguracao() {
        this.telaConfiguracao.setVisible(true);
    }
    
    public void doEscolheMalha(JFileChooser escolheMalha) {
        int retorno = escolheMalha.showOpenDialog(this);
        if(retorno == JFileChooser.APPROVE_OPTION) {
            File arquivo = escolheMalha.getSelectedFile();
            try {
                malha.recriaMalha(arquivo);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado ou não pode ser lido.");
            }
        }
    }

    @Override
    public void configuracaoAlterada() {
        try {
            this.malha.recriaMalha();
        } catch (FileNotFoundException ex) {}
    }
}