package jeu.slide;

import jeu.noyau.ChargeurTexture;
import jeu.noyau.Direction;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2i;

public class Joueur extends Entite {

	private static ChargeurTexture chargeurJoueur = new ChargeurTexture("j.png", new Vector2i(11,18),new Color(222, 230, 10) );
	
	public Joueur() {
		super();
		this.chargeur = chargeurJoueur;
		chargeur.addTexture(this, 0,0);
		this.setOrigin(4,12);
		this.scale(1.2f,1.2f);
		this.setElement(Direction.BAS);
	}
}