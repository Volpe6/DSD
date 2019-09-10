package br.ceavi.udesc.dsd.model;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class Configuracoes {

    private int qtdVeiculos;
    private TipoMonitoracao tipoMonitoracao;

    public Configuracoes() {
        this.qtdVeiculos = 20;
        this.tipoMonitoracao = TipoMonitoracao.MONITOR;
    }

    public int getQtdVeiculos() {
        return qtdVeiculos;
    }

    public void setQtdVeiculos(int qtdVeiculos) {
        this.qtdVeiculos = qtdVeiculos;
    }

    public TipoMonitoracao getTipoMonitoracao() {
        return tipoMonitoracao;
    }

    public void setTipoMonitoracao(TipoMonitoracao tipoMonitoracao) {
        this.tipoMonitoracao = tipoMonitoracao;
    }
}
