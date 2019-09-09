package br.ceavi.udesc.dsd.view;

import br.ceavi.udesc.dsd.controller.ControladorVeiculo;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Jeferson Penz
 */
public class DesenhoVeiculoFantasma extends DesenhoVeiculo{
    
    private Point posicao;
    
    public DesenhoVeiculoFantasma(ControladorVeiculo controlador, Point posicaoNodo) {
        super(controlador);
        this.posicao = posicaoNodo;
    }

    @Override
    protected Color getCor() {
        return Color.RED;
    }

    @Override
    protected Point getPosicao() {
        return this.posicao;
    }

    @Override
    protected void desenhaId(Graphics2D g, int posX, int posY) {}
    
}
