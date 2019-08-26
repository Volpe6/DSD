import View.TelaPrincipal;

/**
 * @author jefip
 * @version 1.0
 * @created 25-ago-2019 12:32:30 PM
 */
public class Main {

	private static Main instance;
	private boolean rodando;
	private TelaPrincipal tela;



	public void finalize() throws Throwable {

	}
	private void Main(){

	}

	public static Main getInstance(){
		return null;
	}

	public TelaPrincipal getTela(){
		return tela;
	}

	public boolean isRodando(){
		return rodando;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){

	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRodando(boolean newVal){
		rodando = newVal;
	}
}//end Main