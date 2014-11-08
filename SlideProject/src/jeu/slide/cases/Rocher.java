package jeu.slide.cases;

import java.io.ObjectStreamException;

import jeu.noyau.Jeu;

import org.jsfml.system.Vector2i;

public class Rocher extends Case {

	private static final long serialVersionUID = 1L;
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
	
	private Object readResolve() throws ObjectStreamException {
		return getInstance();
	}

}
