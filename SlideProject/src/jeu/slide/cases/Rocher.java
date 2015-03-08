package jeu.slide.cases;

import jeu.noyau.render.ViewController;
import jeu.slide.Sprite;

public class Rocher extends Case {
	
	public Rocher(ViewController vc) {
		super(vc, TextureCase.ROCHER);
	}
	
	@Override
	public void collision(Sprite collisioneur) {
		super.collision(collisioneur);
		collisioneur.setMouvement(0,0);
	}

}
