package jeu;

import java.io.IOException;
import java.io.InputStream;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.system.Vector2i;

public class ChargeurTexture {
	private Image image;
	private Vector2i taille;
	
	public interface Element { int ordinal(); }
	
	public ChargeurTexture(InputStream stream,Vector2i taille){
		image = new Image();
		try {
			image.loadFromStream(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.taille = taille;
	}
	
	public ChargeurTexture(InputStream stream, Vector2i taille, Color masque){
		this(stream,taille);
		image.createMaskFromColor(masque);
	}
	
	public Texture getTexture(Element element){
		int ordinal = element.ordinal();
		Texture texture = new Texture();
		IntRect contour = new IntRect( ordinal*taille.x, 0, taille.x, taille.y );
		try {
			texture.loadFromImage(image, contour);
		} catch (TextureCreationException e) {
			e.printStackTrace();
		}
		return texture;
	}
	
}
