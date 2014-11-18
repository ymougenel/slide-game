package jeu.slide;

import jeu.noyau.Jeu;

public class Slide extends Jeu {
	private MenuPauseSlide menuPause;
	private MenuPrincipale menuPrincipale;
	
	public Slide(String nom) {
		super(nom);
		this.menuPause = new MenuPauseSlide();
		this.menuPrincipale = new MenuPrincipale();
		this.charger(menuPrincipale);
	}

	@Override
	public void pause() {
		this.charger(menuPause);
	}

	@Override
	public void menuPrincipal() {
		libererTous();
		this.charger(menuPrincipale);
	}
	
	public static void main(String[] args) {
		Slide sl = new Slide("jeu du Slide");
		sl.run();
	}
}