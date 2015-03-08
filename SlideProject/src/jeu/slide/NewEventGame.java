package jeu.slide;

import jeu.noyau.GameController.EventGame;

public enum NewEventGame implements EventGame {
	
	COUCOU,
	RESTART;
	
	
	private String message="";
	
	public NewEventGame setMessage(String message){
		this.message = message;
		return this;
	}
	
	public String getMessage() {
		return message;
	}

}