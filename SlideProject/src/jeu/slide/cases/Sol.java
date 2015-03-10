package jeu.slide.cases;

import jeu.noyau.Jeu;

import org.jsfml.system.Vector2i;

public class Sol extends Case {
	
	private static Sol singleton = new Sol();
	
	private Sol() {
		super(TextureCase.TERRE);
	}
	
	public static Case getInstance() {
		// TODO Auto-generated method stub
		return singleton;
	}

	@Override
	public Vector2i interaction(Vector2i vitesse, Jeu jeu) {
		// TODO Auto-generated method stub
		return Vector2i.ZERO;
	}
}
