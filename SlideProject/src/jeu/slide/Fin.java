package jeu.slide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import jeu.noyau.Sequence;
import jeu.noyau.render.ChargeurFont;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class Fin extends Sequence implements Serializable{

	private static final long serialVersionUID = 1L;
	private String message="ArcadYann(c) vous félicite!\n ca vous fera 20€";
	private transient Text texte;

	public Fin(Slide game, int id){

		super(game,id);
		
		this.texte= new Text(message, ChargeurFont.Orange.getFont(), 60);
		texte.setPosition( new Vector2f(0, 250));
		
		texte.setColor(Color.RED);
	}

	@Override
	protected void render() {
		if(mode == Mode.Active){
			getGame().getRenderView().draw(texte);
		}
	}
	
	@Override
	public Slide getGame() {
		// TODO Auto-generated method stub
		return (Slide) super.getGame();
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
	protected void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processInputs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processEventGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}
}
