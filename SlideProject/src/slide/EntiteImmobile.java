package slide;

import jeu.Entite;

import org.jsfml.system.Vector2f;

public class EntiteImmobile extends Entite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3399235602263076085L;

	public EntiteImmobile(TextureEntite texture) {
		super(texture);
	}

	@Override
	public boolean setMouvement( Vector2f deplacement) {
		return false;
	}

}
