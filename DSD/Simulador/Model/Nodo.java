package Model;


/**
 * @author jefip
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public abstract class Nodo implements ObservadoDesenho {

	private String id;
	private int posX;
	private int posY;
	public Veiculo veiculoPresente;
	public Nodo nodosAdjacentes;

	public Nodo(){

	}

	public void finalize() throws Throwable {

	}
	public void notificaDesenhoAlterado(){

	}
}//end Nodo