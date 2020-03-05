package jeu.slide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import jeu.noyau.ChargeurFont;
import jeu.noyau.Jeu;
import jeu.noyau.Sequence;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class Fin extends Sequence implements Serializable{

	private static final long serialVersionUID = 1L;
	private String message="ArcadYann(c) congrats you!\n We hope you enjoyed the game";
	private transient Text texte;

	public Fin(){

		this.texte= new Text(message, ChargeurFont.Orange.getFont(), 60);
		texte.setPosition( new Vector2f(0, 250));
		
		texte.setColor(Color.RED);
	}
	@Override
	public void activeUpdate(Jeu game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backgroundUpdate(Jeu game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void render(RenderTarget fenetre) {
		fenetre.draw(texte);
		
	}	
	
	private void readObject(final ObjectInputStream in) throws IOException,  ClassNotFoundException {
		in.defaultReadObject();
		Font font = new Font();
		try {
			font.loadFromStream(Fin.class.getResourceAsStream("/ressources/polices/orangejuice.ttf"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.texte= new Text(message, font, 60);
		texte.setPosition( new Vector2f(0, 250));
		
		texte.setColor(Color.RED);
		
	}
	@Override
	protected void processActiveEvent(Jeu game) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void processActiveEventGame(Jeu game) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void processBackgroundEvent(Jeu game) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void processBackgroundEventGame(Jeu game) {
		// TODO Auto-generated method stub
		
	}


}
