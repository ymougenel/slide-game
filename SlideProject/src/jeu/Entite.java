package jeu;

import java.io.IOException;

import org.jsfml.graphics.Image;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2i;

import slide.Case;

public abstract class Entite extends Sprite{
	protected Vector2i vitesse;
	private  static Vector2i TAILLEENTITE = new Vector2i(16,16);
	public enum TextureEntite {
		ROCHERMOBILE,
		ROCHERIMMOBILE,
		EAU;
	}
	protected static Image image = chargerImage();
	
	private static Image chargerImage(){
			Image image = new Image();
			try {
				image.loadFromStream(Case.class.getResourceAsStream("../sprites/entites.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return image;
	}		

	public Entite(TextureEntite texture) {
		super();
		vitesse=Vector2i.ZERO;
		try {
			int ordinal = texture.ordinal();
			Texture fond = new Texture();
			fond.loadFromImage(image, new IntRect(ordinal*TAILLEENTITE.x, 0, (ordinal+1)*TAILLEENTITE.x, TAILLEENTITE.y) );
			setTexture(fond);
			//sprite.setOrigin(8, 8);
		}
		catch (TextureCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Entite() {
		super();
		vitesse=Vector2i.ZERO;
	}	
	public Vector2i getVitesse(){
		return new Vector2i(vitesse.x,vitesse.y);
	}
	
	public abstract boolean setVitesse(Vector2i vitesse);
	
	public void deplacer( Time t) {
		float distanceX = t.asMilliseconds() * vitesse.x;
		float distanceY = t.asMilliseconds() * vitesse.y;
		this.move(distanceX,distanceY);
	}
}
