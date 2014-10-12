package slide;

import org.jsfml.system.Vector2i;

import jeu.Entite;

public class EntiteMobile extends Entite {

	public EntiteMobile(TextureEntite texture) {
		super(texture);
	}
	
	public EntiteMobile() {
		super();
	}
	@Override
	public boolean setVitesse(Vector2i vitesse) {
		this.vitesse= vitesse;
		return true;
	}

}
