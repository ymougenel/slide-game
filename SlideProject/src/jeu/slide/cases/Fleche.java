package jeu.slide.cases;

import jeu.noyau.Direction;
import jeu.noyau.GameController;
import jeu.noyau.render.ViewController;
import jeu.slide.Joueur;
import jeu.slide.Sprite;

public class Fleche extends Case {

	private Direction sens;
	
	public Fleche(ViewController vc,Direction sens) {
		super(vc, TextureCase.FLECHE);
		render.setRotation(-90*sens.ordinal());
		this.sens=sens;
	}

	@Override
	public void interaction(Sprite sprite, GameController jeu) {
		// TODO Auto-generated method stub
		if ( ecraseur instanceof Joueur) {
			ecraseur.setElement(sens);
		}
		sprite.setMouvement(sens.getSensX(), sens.getSensY());
	}
}
