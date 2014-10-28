package slide;

import java.io.ObjectStreamException;

import org.jsfml.system.Vector2f;

public final class Fleche extends Case {

	private static final long serialVersionUID = 882271584L;
	private Sens sens;
	
	public enum Sens{
		DROITE(new Vector2f(1,0)),
		HAUT(new Vector2f(0,-1)),
		GAUCHE(new Vector2f(-1,0)),
		BAS(new Vector2f(0,1));
		
		private Vector2f sens;
		
		Sens(Vector2f sens){
			this.sens = sens;
		}
		
		public Vector2f getSens(){
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
		super.sprite.setOrigin(8,8);
		super.sprite.setRotation(-90*sens.ordinal());
		this.sens=sens;
	}
	
	public static Case getInstance(Sens sens) {
		// TODO Auto-generated method stub
		return singletons[sens.ordinal()];
	}

	@Override
	public Vector2f interaction(Vector2f vitesse) {
		// TODO Auto-generated method stub
		return sens.getSens();
	}
	
	private Object readResolve() throws ObjectStreamException {
		return getInstance(sens);
	}
}
