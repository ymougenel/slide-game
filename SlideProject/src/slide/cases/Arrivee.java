package slide.cases;

import java.io.ObjectStreamException;

import jeu.Jeu;

import org.jsfml.system.Vector2i;

import slide.NewEventGame;
import slide.cases.Case.TextureCase;

public final class Arrivee extends Case {

	private static final long serialVersionUID = -7702822558882271584L;

	private static Arrivee singleton = new Arrivee();

	public Arrivee() {
		super(TextureCase.ARRIVEE);
	}

	public static Case getInstance() {
		// TODO Auto-generated method stub
		return singleton;
	}

	@Override
	public Vector2i interaction(Vector2i vitesse, Jeu jeu) {
		// TODO Auto-generated method stub
		jeu.ajouterEvenement(NewEventGame.COUCOU);
		return Vector2i.ZERO;
	}

	private Object readResolve() throws ObjectStreamException {
		return getInstance();
	}
}
