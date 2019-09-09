package br.ceavi.udesc.dsd.controller;

import br.ceavi.udesc.dsd.Main;
import br.ceavi.udesc.dsd.model.Configuracoes;
import br.ceavi.udesc.dsd.model.TipoMonitoracao;
import br.ceavi.udesc.dsd.view.ObservadorConfiguracoes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class ControladorConfiguracoes implements ObservadoConfiguracao{

    private Configuracoes configuracoes;
    private List<ObservadorConfiguracoes> observadores = new ArrayList<>();

    public ControladorConfiguracoes() {
        this.configuracoes = Main.getInstance().getConfiguracoes();
    }

    public void salvaConfiguracoes(int qtdVeiculos, TipoMonitoracao tipoMonitoracao) {
        this.configuracoes.setQtdVeiculos(qtdVeiculos);
        this.configuracoes.setTipoMonitoracao(tipoMonitoracao);
        this.notificaConfiguracaoAlterada();
    }
    
    public int getQtdVeiculos(){
        return this.configuracoes.getQtdVeiculos();
    }
    
    public TipoMonitoracao getTipoMonitoracao(){
        return this.configuracoes.getTipoMonitoracao();
    }
    
    public void adicionaObservador(ObservadorConfiguracoes obs){
        this.observadores.add(obs);
    }

    @Override
    public void notificaConfiguracaoAlterada() {
        this.observadores.forEach((observador) -> {
            observador.configuracaoAlterada();
        });
    }
}
