package jeu.slide.jsfml;

import java.util.HashMap;
import java.util.Map;

import org.jsfml.graphics.Color;

import jeu.noyau.Entite;
import jeu.noyau.render.Render;
import jeu.noyau.render.RenderFactory;
import jeu.noyau.render.Renderable;
import jeu.slide.Joueur;
import jeu.slide.Sprite;
import jeu.slide.cases.Arrivee;
import jeu.slide.cases.Case;
import jeu.slide.cases.Fleche;
import jeu.slide.cases.Glace;
import jeu.slide.cases.Rocher;
import jeu.slide.cases.Sol;

public class RenderEntiteJSFMLFactory implements RenderFactory {

	private Map<Class<? extends Renderable>,ChargeurTextureJSFML> chargeurs;
	
	public RenderEntiteJSFMLFactory() {
		this.chargeurs = new HashMap<>();
		ChargeurTextureJSFML chargeur = new ChargeurTextureJSFML("entites.png", Sprite.TAILLEENTITEX,Sprite.TAILLEENTITEY,new Color(222, 230, 10));
		chargeur.charger();
		chargeurs.put(Entite.class, chargeur);
		chargeurs.put(Sprite.class, chargeur);
		chargeur = new ChargeurTextureJSFML("cases.png", Case.TAILLECASEX,Case.TAILLECASEY,new Color(222, 230, 10));
		chargeur.charger();
		chargeurs.put(Case.class, chargeur);
		chargeurs.put(Glace.class, chargeur);
		chargeurs.put(Sol.class, chargeur);
		chargeurs.put(Arrivee.class, chargeur);
		chargeurs.put(Rocher.class, chargeur);
		chargeurs.put(Fleche.class, chargeur);
		
		chargeur = new ChargeurTextureJSFML("j.png", 11,18,new Color(222, 230, 10) );
		chargeur.charger();
		chargeurs.put(Joueur.class, chargeur);
	}

	@Override
	public Render<? extends Renderable> createRender(Renderable renderable) {
		return new RenderEntiteJSFML(chargeurs.get(renderable.getClass()));
	}
}
