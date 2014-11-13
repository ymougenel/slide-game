package jeu.noyau;

import java.io.IOException;

import jeu.noyau.Jeu.EventGame;
import jeu.slide.Fin;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Boutton extends Sprite {

	private Text texte;
	private EventGame event;
	
	public Boutton( Texture texture, EventGame eventgame, String message, Font font) {
		super(texture);
		this.event = eventgame;
		this.texte = new Text(message, font);
	}
	
	public EventGame getEventGame (){
		return event;
	}
	
	public void setPosition ( Vector2f vecteur) {
		super.setPosition(vecteur);
		this.texte.setPosition(vecteur);
	}
	
	public void setOrigine ( Vector2f vecteur) {
		super.setOrigin(vecteur);
		this.texte.setOrigin(vecteur);
	}

	@Override
	public void draw(RenderTarget fenetre, RenderStates status) {
		super.draw(fenetre, status);
		fenetre.draw(texte, status);		
	}

}
