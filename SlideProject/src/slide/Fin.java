package slide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;

import jeu.Jeu;
import jeu.Sequence;

public class Fin extends Sequence implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message="Yann et Arcady vous felicite";
	private transient Text texte;

	public Fin(){
		this.texte= new Text(message, new Font(), 16);
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
		System.out.println("Felecitation!!!");
		fenetre.draw(texte);
		
	}	
	
	private void readObject(final ObjectInputStream in) throws IOException,  ClassNotFoundException {
		in.defaultReadObject();
		this.texte= new Text(message, new Font(), 16);
		texte.setColor(Color.RED);
		
	}

}
