package jeu.slide.cases;

import jeu.noyau.Jeu;

import org.jsfml.system.Vector2i;

public class Arrivee extends Case {
	
	private static Arrivee singleton = new Arrivee();
	
	private Arrivee() {
		super(TextureCase.ARRIVEE);
	}
	
	public static Arrivee getInstance() {
		return singleton;
	}

	@Override
	public Vector2i interaction(Vector2i vitesse, Jeu jeu) {
		return Vector2i.ZERO;
	}
}
