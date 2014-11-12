package jeu.slide;

import java.io.IOException;
import java.io.ObjectInputStream;

import jeu.noyau.ChargeurTexture;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2i;

public class Joueur extends Entite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8558378554755724609L;
	private static ChargeurTexture chargeur = new ChargeurTexture("joueur.png", new Vector2i(11,18),new Color(222, 230, 10) );
	

	public Joueur() {
		super();
		chargeur.addTexture(this, 0);
		this.setOrigin(4,12);
		this.scale(1.2f,1.2f);
	}
	private void readObject(final ObjectInputStream in) throws IOException,  ClassNotFoundException {
		in.defaultReadObject();
		chargeur.addTexture(this, 0);
		this.setOrigin(8,16);
	}
}