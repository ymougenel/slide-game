package jeu.slide.cases;

import java.io.ObjectStreamException;

import jeu.noyau.Jeu;

import org.jsfml.system.Vector2i;

public final class Fleche extends Case {

	private static final long serialVersionUID = 882271584L;
	private Sens sens;
	
	public enum Sens{
		DROITE(new Vector2i(1,0)),
		HAUT(new Vector2i(0,-1)),
		GAUCHE(new Vector2i(-1,0)),
		BAS(new Vector2i(0,1));
		
		private Vector2i sens;
		
		Sens(Vector2i sens){
			this.sens = sens;
		}
		
		public Vector2i getSens(){
			return sens;
		}
	}
	private static Fleche[] singletons = new Fleche[4];
	
	static{
	singletons[0] = new Fleche(Sens.DROITE);
	singletons[1] = new Fleche(Sens.HAUT);
	singletons[2] = new Fleche(Sens.GAUCHE);
	singletons[3] = new Fleche(Sens.BAS);
	}
	
	private Fleche(Sens sens) {
		super(TextureCase.FLECHE);
		super.sprite.setRotation(-90*sens.ordinal());
		this.sens=sens;
	}
	
	public static Case getInstance(Sens sens) {
		// TODO Auto-generated method stub
		return singletons[sens.ordinal()];
	}

	@Override
	public Vector2i interaction(Vector2i vitesse, Jeu jeu) {
		// TODO Auto-generated method stub
		return sens.getSens();
	}
	
	private Object readResolve() throws ObjectStreamException {
		return getInstance(sens);
	}
}