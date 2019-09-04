package br.ceavi.udesc.dsd.model;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 */
public enum TipoMonitoracao {
    SEMAFORO("Semáforo"), MONITOR("Monitor");
    private String tipo;
    TipoMonitoracao(String tipo){
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return this.tipo;
    }
}
