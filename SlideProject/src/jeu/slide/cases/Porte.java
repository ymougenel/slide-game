package jeu.slide.cases;

import jeu.noyau.GameController;
import jeu.noyau.render.ViewController;
import jeu.slide.Sprite;
import jeu.slide.Sprite.TextureEntite;

public class Porte extends Case {
	
	private boolean porteFermee;
	private Sprite cle;

	public Porte(ViewController vc) {
		super(vc, TextureCase.PORTE);
		porteFermee=true;
		cle=null;
	}

	@Override
	public void interaction(Sprite sprite, GameController jeu) {
		if(cle !=null){
			cle.setFantome(true);
			cle=null;
			render.setTexture(TextureCase.PORTE, 1);
		}
		super.interaction(sprite, jeu);
	}
	
	@Override
	public void collision(Sprite collisioneur) {
		super.collision(collisioneur);
		if(porteFermee){
			if(collisioneur.getElement() == TextureEntite.CLE){
				porteFermee=false;
				cle=collisioneur;
			}else{
				collisioneur.setMouvement(0,0);
			}
		}
	}
}
