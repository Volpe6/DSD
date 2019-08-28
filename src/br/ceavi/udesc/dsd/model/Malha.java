package br.ceavi.udesc.dsd.model;

import br.ceavi.udesc.dsd.view.ObservadorDesenho;
import br.ceavi.udesc.dsd.view.ObservadorMalha;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class Malha implements ObservadoMalha, ObservadoDesenho {

    private Configuracoes configuracao;
    private List<Nodo> nodos;
    private List<ObservadorMalha> obsMalha;
    private List<ObservadorDesenho> obsDesenho;

    public Malha() {
        this.configuracao = new Configuracoes();
    }

    public void criaVeiculo() {

    }

    public void recriaNodos(String malha) {
        this.nodos = new ArrayList<Nodo>();
    }

    public Configuracoes getConfiguracao() {
        return configuracao;
    }

    public List<Nodo> getNodos() {
        return this.nodos;
    }
    
    public void adicionaObsMalha(ObservadorMalha obs){
        this.obsMalha.add(obs);
    }
    
    public void adicionaObsDesenho(ObservadorDesenho obs){
        this.obsDesenho.add(obs);
    }

    @Override
    public void notificaMalhaCarregada() {
        this.obsMalha.forEach((obs) -> {
            obs.malhaCarregada();
        });
    }

    @Override
    public void notificaVeiculoCriado(Veiculo veiculo) {
        this.obsMalha.forEach((obs) -> {
            obs.veiculoCriado(veiculo);
        });
    }

    @Override
    public void notificaVeiculoRemovido(String id) {
        this.obsMalha.forEach((obs) -> {
            obs.veiculoRemovido(id);
        });
    }

    @Override
    public void notificaDesenhoAlterado() {
        this.obsDesenho.forEach((obs) -> {
            obs.desenhoAlterado();
        });
    }
}
