package br.ceavi.udesc.dsd.model;

import br.ceavi.udesc.dsd.view.ObservadorConfiguracoes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class Configuracoes implements ObservadoConfiguracao {

    private int qtdVeiculos;
    private int tipoMonitoracao;
    private List<ObservadorConfiguracoes> observadores = new ArrayList<>();

    public Configuracoes() {

    }

    public int getQtdVeiculos() {
        return qtdVeiculos;
    }

    public void setQtdVeiculos(int qtdVeiculos) {
        this.qtdVeiculos = qtdVeiculos;
    }

    public int getTipoMonitoracao() {
        return tipoMonitoracao;
    }

    public void setTipoMonitoracao(int tipoMonitoracao) {
        this.tipoMonitoracao = tipoMonitoracao;
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
