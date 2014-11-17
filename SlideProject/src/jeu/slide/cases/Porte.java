package jeu.slide.cases;

import jeu.noyau.Jeu;
import jeu.slide.Entite;
import jeu.slide.Entite.TextureEntite;

import org.jsfml.system.Vector2i;

public class Porte extends Case {
	
	private boolean porteFermee;
	private Entite cle;

	public Porte() {
		super(TextureCase.PORTE_FERMEE);
		porteFermee=true;
		cle=null;
	}

	@Override
	public Vector2i interaction(Vector2i vitesse, Jeu jeu) {
		if(cle !=null){
			cle.setFantome(true);
			cle=null;
			chargeur.addTexture(sprite, TextureCase.PORTE_OUVERTE, 0);
		}
		return Vector2i.ZERO;
	}
	
	@Override
	public void collision(Entite collisioneur) {
		super.collision(collisioneur);
		if(porteFermee){
			if(collisioneur.getElement() == TextureEntite.CLE){
				porteFermee=false;
				cle=collisioneur;
			}else{
				collisioneur.setMouvement(Vector2i.ZERO);
			}
		}
	}

}
