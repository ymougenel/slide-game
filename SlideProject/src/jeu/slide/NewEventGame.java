package jeu.slide;

import jeu.noyau.Jeu.EventGame;

public enum NewEventGame implements EventGame {
	
	COUCOU,
	DEBUTPARTIE,
	CHARGER_MENU_PRINCIPALE,
	CHARGER_MENU_PAUSE,
	CHARGER_PLATEAU,
	RESTART,
	QUITTER;
	
	
	private String message="";
	
	public NewEventGame setMessage(String message){
		this.message = message;
		return this;
	}
	
	public String getMessage() {
		return message;
	}

}