package br.ceavi.udesc.dsd.model;

/**
 *
 * @author Jeferson Penz
 */
public enum Direcao {
    
    NORTE(1, 0), SUL(0, 1), LESTE(1, 1), OESTE(0, 0);
    
    private int mulX;
    private int mulY;
    Direcao(int mulX, int mulY){
        this.mulX = mulX;
        this.mulY = mulY;
    }

    public int getMulX() {
        return mulX;
    }

    public int getMulY() {
        return mulY;
    }
    
    public Direcao getDirecaoOposta(){
        switch(this){
            case NORTE:
                return SUL;
            case SUL:
                return NORTE;
            case LESTE:
                return OESTE;
            case OESTE:
                return LESTE;
        }
        return null;
    }
    
}
