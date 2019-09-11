package br.ceavi.udesc.dsd.view;

import br.ceavi.udesc.dsd.Main;
import br.ceavi.udesc.dsd.controller.ControladorVeiculo;
import br.ceavi.udesc.dsd.model.Direcao;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class DesenhoVeiculo extends Desenho {

    private ControladorVeiculo controlador;

    public DesenhoVeiculo(ControladorVeiculo controlador) {
        this.controlador = controlador;
    }
    
    protected Color getCor(){
        return Color.YELLOW;
    }
    protected Color getCorId(){
        return Color.RED;
    }

    @Override
    public void desenha(Graphics2D g) {
        int grid = Main.getInstance().getTamanhoBlocoGrid();
        g.setColor(this.getCor());
        Point posicao = this.getPosicao();
        Direcao direcao = this.getDirecao();
        if(posicao == null || direcao == null){
            return;
        }
        int posX = posicao.x * grid;
        int posY = posicao.y * grid;
        g.fillOval(posX, posY, grid, grid);
        
        if(Main.DEPURANDO){
            this.desenhaId(g, posX, posY);
        }
    }
    
    protected Point getPosicao(){
        return this.controlador.getPosicaoVeiculo();
    }
    
    protected Direcao getDirecao(){
        return this.controlador.getDirecaoVeiculo();
    }
    
    protected void desenhaId(Graphics2D g, int posX, int posY){
        g.setColor(this.getCorId());
        g.drawString(this.controlador.getIdVeiculo(), posX, posY);
    }
}