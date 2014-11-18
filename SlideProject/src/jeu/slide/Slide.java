package jeu.slide;

import java.io.IOException;

import jeu.noyau.Jeu;
import jeu.noyau.Sequence;

import org.jsfml.graphics.TextureCreationException;

public class Slide extends Jeu {
	private Sequence menuPause;
	private Sequence menuPrincipale;
	
	public Slide(String nom) {
		super(nom);
		this.menuPause = new MenuPauseSlide();
		this.menuPrincipale = new MenuPrincipale();
		this.charger(menuPrincipale);
	}
	
	@Override
	protected void processEventGame(EventGame event) {
		if(event instanceof NewEventGame){
			switch( (NewEventGame) event ){
				default:
			}
		}
		if(event == NewEventGame.COUCOU){
			System.out.println("non");
		}
	}

	public static void main(String[] args) throws IOException, TextureCreationException {
		Slide sl = new Slide("jeu du Slide");
		sl.run();
	}

	@Override
	public void pause() {
		this.charger(menuPause);
	}

	@Override
	public void menuPrincipal() {
		this.sequencesChargees.clear();
		this.charger(menuPrincipale);
	}
	
}