package slide;

import jeu.Jeu.EventGame;

public enum NewEventGame implements EventGame {
	
	COUCOU,
	CHARGERNIVEAU;
	
	private String message="";
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}