package jeu;

import java.io.IOException;
import java.io.InputStream;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.system.Vector2i;

public class ChargeurTexture {
	private Texture texture;
	private Vector2i taille;
	
	public interface Element { int ordinal(); }
	
	public ChargeurTexture(String name,Vector2i taille){
		texture = new Texture();
		try (InputStream stream = getClass().getResourceAsStream("/sprites/"+name)){
			texture.loadFromStream(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.taille=taille;
	}
	
	public ChargeurTexture(String name, Vector2i taille, Color masque){
		Image image = new Image();
		try (InputStream stream = getClass().getResourceAsStream("/sprites/"+name)){
			image.loadFromStream(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		image.createMaskFromColor(masque);
		texture = new Texture();
		try {
			texture.loadFromImage(image);
		} catch (TextureCreationException e) {
			e.printStackTrace();
		}
		this.taille=taille;
	}
	
	public void addTexture(Sprite sprite, Element element){
		addTexture(sprite, element.ordinal());
	}
	
	public void addTexture(Sprite sprite, int element){
		IntRect contour = new IntRect( element*taille.x, 0, taille.x, taille.y );
		sprite.setTexture(texture);
		sprite.setTextureRect(contour);
	} 
	
}
