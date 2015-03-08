package jeu.slide;

import jeu.noyau.Direction;
import jeu.noyau.GameController;
import jeu.slide.jsfml.ChargeurTextureJSFML;

import org.jsfml.graphics.Color;

public class Joueur extends Sprite {

	private static ChargeurTextureJSFML chargeurJoueur = new ChargeurTextureJSFML("j.png", 11,18,new Color(222, 230, 10) );
	
	public Joueur(GameController game) {
		super(game);
		render.setOrigin(4,12);
		render.setScale(1.2f,1.2f);
		this.setElement(Direction.BAS);
	}
}