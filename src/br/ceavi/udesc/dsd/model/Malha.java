package br.ceavi.udesc.dsd.model;

import br.ceavi.udesc.dsd.Main;
import br.ceavi.udesc.dsd.controller.ObservadoEstadoVeiculo;
import br.ceavi.udesc.dsd.controller.ObservadorEstadoVeiculo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class Malha implements ObservadoEstadoVeiculo{

    private Configuracoes configuracao;
    private Map<String, Nodo> nodos;
    private List<String> nodosBorda;
    private List<Veiculo> veiculos;
    private List<ObservadorEstadoVeiculo> obsVeiculo;

    public Malha() {
        this.configuracao = Main.getInstance().getConfiguracoes();
        this.nodos = new HashMap<>();
        this.veiculos = new ArrayList<>();
        this.nodosBorda = new ArrayList<>();
        this.obsVeiculo = new ArrayList<>();
    }
    
    public Nodo criaNodo(boolean borda){
        Nodo nodo = null;
        switch(this.getConfiguracao().getTipoMonitoracao()){
            case MONITOR:
                nodo = new NodoMonitor();
                break;
            case SEMAFORO:
            default:
                nodo = new NodoSemaforo();
                break;
        }
        this.nodos.put(nodo.getId(), nodo);
        if(borda){
            this.nodosBorda.add(nodo.getId());
            nodo.setBorda(true);
        }
        return nodo;
    }

    public List<String> getNodosBorda() {
        return nodosBorda;
    }

    public Veiculo criaVeiculo() {
        Nodo nodoInicial = getNodoInicialVeiculo();
        if(nodoInicial == null){
            return null;
        }
        Veiculo veiculo = null;
        switch(this.getConfiguracao().getTipoMonitoracao()){
            case MONITOR:
                veiculo = new VeiculoMonitor(nodoInicial);
                break;
            case SEMAFORO:
            default:
                veiculo = new VeiculoSemaforo(nodoInicial);
                break;
        }
        this.veiculos.add(veiculo);
        return veiculo;
    }
    
    private Nodo getNodoInicialVeiculo(){
        int posRandom = (int) (Math.random() * (float) this.getNodosBorda().size());
        Nodo nodoAlvo = this.nodos.get(this.getNodosBorda().get(posRandom));
        if(!nodoAlvo.isVeiculoPresente(nodoAlvo.getDirecaoInicial())){
            return nodoAlvo;
        }
        return null;
    }
    
    public void removeVeiculo(Veiculo veiculo){
        this.veiculos.remove(veiculo);
    }
    
    public int getTotalVeiculos(){
        return this.veiculos.size();
    }
    
    public void limpaVeiculos(){
        if(this.nodos.size() > 0){
            this.nodos.entrySet().stream().map((entry) -> {
                String key = entry.getKey();
                return entry;
            }).forEachOrdered((entry) -> {
                Nodo nodo = entry.getValue();
                nodo.getVeiculoPresente().entrySet().stream().map((veiEnt) -> {
                    Direcao key = veiEnt.getKey();
                    return veiEnt;
                }).forEachOrdered((veiEnt) -> {
                    Veiculo value = veiEnt.getValue();
                    notificaoVeiculoRemovido(value);
                });
                nodo.limpaVeiculos();
            });
        }
        this.veiculos = new ArrayList<>();
    }

    public void recriaNodos() {
        if(this.nodos.size() > 0){
            this.nodos.entrySet().stream().map((entry) -> {
                String key = entry.getKey();
                return entry;
            }).forEachOrdered((entry) -> {
                Nodo nodo = entry.getValue();
                nodo.limpaNodosAdjacentes();
            });
        }
        this.nodos = new HashMap<>();
        this.nodosBorda = new ArrayList<>();
    }

    public Configuracoes getConfiguracao() {
        return configuracao;
    }

    public Map<String, Nodo> getNodos() {
        return this.nodos;
    }

    public Nodo getNodo(String id) {
        return this.nodos.get(id);
    }
    
    public int getTamanho(){
        return this.nodos.size();
    }
    
    public void addObservadorEstadoVeiculo(ObservadorEstadoVeiculo obs){
        this.obsVeiculo.add(obs);
    }

    @Override
    public void notificaoVeiculoRemovido(Veiculo veiculo) {
        this.obsVeiculo.forEach((ob) -> {
            ob.veiculoRemovido(veiculo);
        });
    }

    public List<Veiculo> getAllVeiculos() {
        return this.veiculos;
    }
}
