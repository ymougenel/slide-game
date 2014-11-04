package slide.cases;

import java.io.Serializable;

import jeu.ChargeurTexture;
import jeu.Jeu;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2i;

public abstract class Case implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1622952502425902144L;
	protected  transient Sprite sprite;
	public static final Vector2i TAILLECASE = new Vector2i(16, 16);
	
	protected static ChargeurTexture chargeur = new ChargeurTexture("cases.png", TAILLECASE);
	
	public enum TextureCase implements ChargeurTexture.Element{
		GLACE,
		TERRE,
		EAU,
		MOUSSE,
		FLECHE,
		DEPART,
		ARRIVEE;
	}
	public Case (TextureCase texture){
		
		sprite= new Sprite();
		chargeur.addTexture(sprite, texture);
		
	}
	
	public  Sprite getSprite() {
		return this.sprite;
	}
	public abstract Vector2i interaction(Vector2i vitesse, Jeu jeu);
	
	public static Vector2i getTailleCase() {
		return TAILLECASE;
	}
}
