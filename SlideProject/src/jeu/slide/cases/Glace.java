package jeu.slide.cases;

import jeu.noyau.Jeu;

import org.jsfml.system.Vector2i;

public class Glace extends Case {
	
	private static Glace singleton = new Glace();
	
	private Glace() {
		super(TextureCase.GLACE);
	}
	
	public static Case getInstance() {
		// TODO Auto-generated method stub
		return singleton;
	}

	@Override
	public Vector2i interaction(Vector2i vitesse, Jeu jeu) {
		// TODO Auto-generated method stub
		return vitesse;
	}
}
