package jeu.slide.cases;

import jeu.noyau.Direction;
import jeu.noyau.Jeu;
import jeu.slide.Joueur;

import org.jsfml.system.Vector2i;

public class Fleche extends Case {

	private Direction sens;
	
	private static Fleche[] singletons = new Fleche[4];
	
	static{
	singletons[0] = new Fleche(Direction.DROITE);
	singletons[1] = new Fleche(Direction.HAUT);
	singletons[2] = new Fleche(Direction.GAUCHE);
	singletons[3] = new Fleche(Direction.BAS);
	}
	
	private Fleche(Direction sens) {
		super(TextureCase.FLECHE);
		super.sprite.setRotation(-90*sens.ordinal());
		this.sens=sens;
	}
	
	public static Case getInstance(Direction sens) {
		// TODO Auto-generated method stub
		return singletons[sens.ordinal()];
	}

	@Override
	public Vector2i interaction(Vector2i vitesse, Jeu jeu) {
		// TODO Auto-generated method stub
		if ( ecraseur instanceof Joueur) {
			ecraseur.setElement(sens);
		}
		return sens.getSens();
	}
}
