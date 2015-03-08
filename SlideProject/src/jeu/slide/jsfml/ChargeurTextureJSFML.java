package jeu.slide.jsfml;

import java.io.IOException;
import java.io.InputStream;

import jeu.noyau.Element;
import jeu.noyau.render.ChargeurTexture;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;

public class ChargeurTextureJSFML extends ChargeurTexture {
	private Texture texture;
	private Color masque;
	
	public ChargeurTextureJSFML(String name,int tailleX,int tailleY){
		super("/ressources/sprites/"+name,tailleX,tailleY);
	}
	
	public ChargeurTextureJSFML(String name, int tailleX, int tailleY, Color masque){
		this(name,tailleX,tailleY);
		this.masque = masque;
	}
	
	@Override
	public void charger() {
		Image image = new Image();
		try (InputStream stream = getClass().getResourceAsStream(getPath())){
			image.loadFromStream(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(masque != null){
			image.createMaskFromColor(masque);
		}
		texture = new Texture();
		try {
			texture.loadFromImage(image);
		} catch (TextureCreationException e) {
			e.printStackTrace();
		}	
	}
	
	public void addTexture(RenderEntiteJSFML render, Element element,int trame){
		super.addTexture(render, element, trame);
		render.getSprite().setTexture(texture);
	} 
	
}
