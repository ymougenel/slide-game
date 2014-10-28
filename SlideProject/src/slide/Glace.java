package slide;

import java.io.ObjectStreamException;

import org.jsfml.system.Vector2f;

public final class Glace extends Case {

	private static final long serialVersionUID = -7702822558882271584L;
	
	private static Glace singleton = new Glace();
	
	public Glace() {
		super(TextureCase.GLACE);
	}
	
	public static Case getInstance() {
		// TODO Auto-generated method stub
		return singleton;
	}

	@Override
	public Vector2f interaction(Vector2f vitesse) {
		// TODO Auto-generated method stub
		return vitesse;
	}
	
	private Object readResolve() throws ObjectStreamException {
		return getInstance();
	}
}
