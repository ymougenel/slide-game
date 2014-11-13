package jeu.slide;

import jeu.noyau.Jeu.EventGame;

public enum NewEventGame implements EventGame {
	
	COUCOU,
	DEBUTPARTIE,
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