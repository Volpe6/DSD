package br.ceavi.udesc.dsd.view;

import br.ceavi.udesc.dsd.Main;
import br.ceavi.udesc.dsd.controller.ControladorMalha;
import br.ceavi.udesc.dsd.model.Direcao;
import br.ceavi.udesc.dsd.model.Nodo;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * @author Andrew Vinicius da Silva Baasch, Jeferson Penz
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class DesenhoNodo extends Desenho {

    private String id;
    private ControladorMalha controlador;

    public DesenhoNodo(ControladorMalha controlador, String id) {
        super();
        this.controlador = controlador;
        this.id = id;
    }

    @Override
    public void desenha(Graphics2D g) {
        int grid = Main.getInstance().getTamanhoBlocoGrid();
        Point posicao = this.controlador.getPosicaoNodo(id);
        g.setColor(new Color(100, 100, 100));
        g.fillRect(posicao.x * grid, posicao.y * grid, grid, grid);
        
//        if(posicao.y == 1 && posicao.x ==14) {
//        
//        }
        
        Nodo n = controlador.getNodo(id);
//        if(n.isBorda()) {
//            System.out.println("borda");
//            System.out.println("x:" + (n.getPosX() + 1) + "y:" + (n.getPosY() + 1));
//        }
        g.setColor(Color.red);
//        g.drawString("x:"+ posicao.x , posicao.x * grid, posicao.y * grid+10);
//        g.drawString("x:"+ posicao.x + "y:"+ posicao.y , posicao.x * (grid + 10), posicao.y * grid+10);
        g.drawString(""+ n.getNumero(), posicao.x * grid, posicao.y * grid+10);
        if(Main.DEPURANDO){
            this.controlador.getControladoresVeiculos(id).entrySet().stream().map((entry) -> {
                Direcao key = entry.getKey();
                return entry;
            }).map((entry) -> entry.getValue()).forEachOrdered((value) -> {
                new DesenhoVeiculoFantasma(value, posicao).desenha(g);
            });
        }
    }

}