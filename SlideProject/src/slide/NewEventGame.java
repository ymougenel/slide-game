package slide;

import jeu.Jeu.EventGame;

public enum NewEventGame implements EventGame {
	
	COUCOU,
	CHARGERNIVEAU;
	
	private int numero=0;
	
	public void setMessage(int niveau){
		this.numero = niveau;
	}
	
	public int getMessage() {
		return numero;
	}

}