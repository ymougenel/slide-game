package jeu.noyau;

import jeu.slide.cases.Case.TextureCase;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2i;

public abstract class Item extends Sprite{

	/**
	 * @param args
	 */
	protected Sprite sprite;
	public static final Vector2i TAILLECASE = new Vector2i(16, 16);
	protected static ChargeurTexture chargeur = new ChargeurTexture("item.png", TAILLECASE, new Color(222, 230, 10));
	
	public enum TextureItem implements ChargeurTexture.Element{
		POMME;

		@Override
		public int getNombreTrames() {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
	public Item (TextureItem texture){
		
		sprite= new Sprite();
		chargeur.addTexture(sprite, texture,0);
		sprite.setOrigin(8,8);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setPosition(int i, int j) {
		// TODO Auto-generated method stub
		
	}

}
