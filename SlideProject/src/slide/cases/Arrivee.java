package slide.cases;

import java.io.IOException;
import java.io.ObjectInputStream;

import jeu.Jeu;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2i;

import slide.NewEventGame;

public class Arrivee extends Case {

	private static final long serialVersionUID = -7702822558882271584L;
	private String destination;
	
	public Arrivee (String dest){
		super(TextureCase.ARRIVEE);
		this.destination = dest;
	}

	public Arrivee() {
		super(TextureCase.ARRIVEE);
	}

	@Override
	public Vector2i interaction(Vector2i vitesse, Jeu jeu) {
		// TODO Auto-generated method stub
		NewEventGame.CHARGERNIVEAU.setMessage(destination);
		jeu.ajouterEvenement(NewEventGame.CHARGERNIVEAU);
		return Vector2i.ZERO;
	}

	private void readObject(final ObjectInputStream in) throws IOException,  ClassNotFoundException {
		in.defaultReadObject();
		if (TextureCase.ARRIVEE != null) {
			sprite = new Sprite( chargeur.getTexture(TextureCase.ARRIVEE) );
		}
			//sprite.setOrigin(8, 8);
	}
}
