package slide;

import java.io.IOException;
import java.io.ObjectInputStream;

import jeu.Entite;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;

public class Joueur extends Entite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8558378554755724609L;

	public Joueur() {
		super();
		Image image = new Image();
		try {
			image.loadFromStream( Joueur.class.getResourceAsStream("../sprites/joueur.png") );
			image.createMaskFromColor( new Color(222, 230, 10) );
			Texture texture = new Texture();
			texture.loadFromImage(image);
			this.setTexture(texture);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TextureCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private void readObject(final ObjectInputStream in) throws IOException,  ClassNotFoundException {
		in.defaultReadObject();
		Image image = new Image();
		try {
			image.loadFromStream( Joueur.class.getResourceAsStream("../sprites/joueur.png") );
			image.createMaskFromColor( new Color(222, 230, 10) );
			Texture texture = new Texture();
			texture.loadFromImage(image);
			this.setTexture(texture);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TextureCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}