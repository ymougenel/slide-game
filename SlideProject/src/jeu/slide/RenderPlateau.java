package jeu.slide;

import jeu.noyau.Sequence;
import jeu.noyau.render.RenderSequence;
import jeu.noyau.render.ViewController;
import jeu.slide.cases.Case;
import jeu.slide.jsfml.RenderEntiteJSFML;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.View;

public class RenderPlateau implements RenderSequence {
	
	public RenderPlateau() {
	}
	
	
	@Override
	public void render(ViewController vc, Sequence seq) {
		Slide slide = (Slide) vc;
		Plateau plateau = (Plateau) seq;
		int tx = plateau.damierCases.length;
		int ty = plateau.damierCases[0].length;
		slide.getRenderView().setView(new View(new FloatRect(-8, -8, 16*(tx), 16*(ty))));
		int i, j;
		for (i = 0; i < plateau.damierCases.length; i++) {
			for (j = 0; j < plateau.damierCases[0].length; j++) {
				/* Traitement des cases du pleateau */
				plateau.damierCases[i][j].getRender().setPosition(i * Case.TAILLECASEX, j * Case.TAILLECASEY);
				plateau.damierCases[i][j].draw(vc);
				org.jsfml.graphics.Sprite sprite = ((RenderEntiteJSFML)plateau.damierCases[i][j].getRender()).getSprite();
				//System.out.println("text "+sprite.getTexture());
			}
		}
		for (i = 0; i < plateau.damierEntite.length; i++) {
			for (j = 0; j < plateau.damierEntite[0].length; j++) {
				/* Traitement des entites du plateau */
				if (plateau.damierEntite[i][j] != null) {
					plateau.damierEntite[i][j].draw(vc);
				}
			}
		}

		if (plateau.decompteur>0 ){
			slide.getRenderView().setView(slide.getRenderView().getDefaultView());
			plateau.decompteur--;
			if (4*plateau.decompteur <256){
				plateau.texteDebut.setColor(new Color(Color.BLUE, 4*plateau.decompteur));
			}
			slide.getRenderView().draw(plateau.texteDebut);
		}
	}

}
