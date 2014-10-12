package slide;

import java.io.IOException;

import jeu.Entite;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.system.Vector2i;

public class Joueur extends EntiteMobile {
	private Image image;

	public Joueur() {
		super();
		image = new Image();
		try {
			image.loadFromStream( Joueur.class.getResourceAsStream("../sprites/joueur.png") );
			image.createMaskFromColor( new Color(151, 155, 49) );
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

	@Override
	public boolean setVitesse(Vector2i vitesse) {
		this.vitesse = new Vector2i(vitesse.x,vitesse.y);
		return true;
	}

}