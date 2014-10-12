package slide;

import org.jsfml.system.Vector2i;

import jeu.Entite;

public class EntiteImmobile extends Entite {

	public EntiteImmobile(TextureEntite texture) {
		super(texture);
	}

	@Override
	public boolean setVitesse(Vector2i vitesse) {
		return false;
	}

}
