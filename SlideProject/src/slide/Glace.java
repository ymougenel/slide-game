package slide;

import org.jsfml.system.Vector2i;

public final class Glace extends Case {

	private static Glace singleton = new Glace();
	
	public Glace() {
		super(TextureCase.GLACE);
	}
	
	public static Case getInstance() {
		// TODO Auto-generated method stub
		return singleton;
	}

	@Override
	public Vector2i interaction(Vector2i vitesse) {
		// TODO Auto-generated method stub
		return vitesse;
	}
}
