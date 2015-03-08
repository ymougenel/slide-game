package jeu.slide.jsfml;

import jeu.slide.cases.Case;

public class RenderCaseJSFML extends RenderEntiteJSFML {
	
	public RenderCaseJSFML(ChargeurTextureJSFML chargeur) {
		super(chargeur);
		// TODO Auto-generated constructor stub
	}
	public static ChargeurTextureJSFML chargeur;
	static{
		chargeur = new ChargeurTextureJSFML("cases.png", Case.TAILLECASEX,Case.TAILLECASEY);
		chargeur.charger();
	}

}
