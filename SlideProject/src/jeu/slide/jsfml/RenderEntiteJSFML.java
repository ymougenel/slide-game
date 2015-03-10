package jeu.slide.jsfml;

import jeu.noyau.Element;
import jeu.noyau.Entite;
import jeu.noyau.render.ChargeurTexture;
import jeu.noyau.render.RenderEntite;
import jeu.noyau.render.ViewController;
import jeu.slide.Slide;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class RenderEntiteJSFML implements RenderEntite {
	
	//private static ChargeurTextureJSFML chargeurEntite;
	//static{
	//	chargeurEntite = new ChargeurTextureJSFML("entites.png", Sprite.TAILLEENTITEX,Sprite.TAILLEENTITEY,new Color(222, 230, 10) );
	//	chargeurEntite.charger();
	//}
	
	private org.jsfml.graphics.Sprite sprite;
	private ChargeurTextureJSFML chargeur;
	
	public RenderEntiteJSFML(ChargeurTextureJSFML chargeur) {
		super();
		this.chargeur = chargeur;
		chargeur.charger();
		sprite = new  Sprite();
	}
	
	public org.jsfml.graphics.Sprite getSprite() {
		return sprite;
	}

	@Override
	public void render(ViewController vc, Entite entite) {
		RenderWindow fenetre = ((Slide)vc).getRenderView();
		fenetre.draw(sprite);
	}

	@Override
	public float getPositionX() {
		return sprite.getPosition().x;
	}

	@Override
	public float getPositionY() {
		// TODO Auto-generated method stub
		return sprite.getPosition().y;
	}

	@Override
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
	}

	@Override
	public void setTextureRect(int x, int y, int tailleX, int tailleY) {
		sprite.setTextureRect(new IntRect(x, y, tailleX, tailleY));
		
	}

	@Override
	public void setOrigin(int x, int y) {
		sprite.setOrigin(x, y);
	}

	@Override
	public void setScale(float x, float y) {
		sprite.scale(x, y);
	}

	@Override
	public void setRotation(float angle) {
		sprite.setRotation(angle);
	}

	@Override
	public ChargeurTexture getChargeur() {
		return chargeur;
	}

	@Override
	public void setTexture(Element element, int trame) {
		chargeur.addTexture(this, element, trame);
	}

	@Override
	public void init(Entite renderable) {
		// TODO Auto-generated method stub
		
	}
}
