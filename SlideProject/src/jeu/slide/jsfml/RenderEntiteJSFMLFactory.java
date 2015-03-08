package jeu.slide.jsfml;

import jeu.noyau.render.RenderFactory;

public class RenderEntiteJSFMLFactory implements RenderFactory {

	private ChargeurTextureJSFML chargeur;
	
	public RenderEntiteJSFMLFactory(ChargeurTextureJSFML chargeur) {
		this.chargeur = chargeur;
	}
	
	@Override
	public RenderEntiteJSFML createRender() {
		return new RenderEntiteJSFML(chargeur);
	}

}
