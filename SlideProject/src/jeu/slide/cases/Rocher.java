package jeu.slide.cases;

import jeu.noyau.Jeu;
import jeu.slide.Entite;

import org.jsfml.system.Vector2i;

public class Rocher extends Case {

	private static Rocher singleton = new Rocher();
	
	private Rocher() {
		super(TextureCase.ROCHER);
	}
	
	public static Rocher getInstance() {
		return singleton;
	}

	@Override
	public Vector2i interaction(Vector2i vitesse, Jeu jeu) {
		return Vector2i.ZERO;
	}
	
	@Override
	public void collision(Entite collisioneur) {
		super.collision(collisioneur);
		collisioneur.setMouvement(Vector2i.ZERO);
	}

}
