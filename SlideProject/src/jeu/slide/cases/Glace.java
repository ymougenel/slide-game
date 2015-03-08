package jeu.slide.cases;

import jeu.noyau.GameController;
import jeu.noyau.render.ViewController;
import jeu.slide.Sprite;

public class Glace extends Case {
	
	public Glace(ViewController vc) {
		super(vc, TextureCase.GLACE);
	}

	@Override
	public void interaction(Sprite sprite, GameController jeu) {
		sprite.setMouvement(sprite.getMouvementX(), sprite.getMouvementY());
	}
}
