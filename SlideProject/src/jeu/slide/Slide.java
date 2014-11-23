package jeu.slide;

import jeu.noyau.Jeu;

public class Slide extends Jeu {
	
	public Slide() {
		super("Slide", new MenuPauseSlide(), new MenuPrincipale());
	}

	public static void main(String[] args) {
		Slide slide = new Slide();
		slide.run();
	}
}