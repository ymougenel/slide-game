package slide;

import java.io.ObjectStreamException;

import org.jsfml.system.Vector2f;

public final class Sol extends Case {

	private static final long serialVersionUID = 1L;
	
	private static Sol singleton = new Sol();
	
	public Sol() {
		super(TextureCase.TERRE);
	}
	
	public static Case getInstance() {
		// TODO Auto-generated method stub
		return singleton;
	}

	@Override
	public Vector2f interaction(Vector2f vitesse) {
		// TODO Auto-generated method stub
		return Vector2f.ZERO;
	}
	
	private Object readResolve() throws ObjectStreamException {
		return getInstance();
	}
}
