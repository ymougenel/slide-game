package jeu.slide.cases;

import jeu.noyau.GameController;
import jeu.noyau.render.ViewController;
import jeu.slide.Sprite;

public class Ice extends Case{
		
		private boolean craquee;

		public Ice(ViewController vc) {
			super(vc,TextureCase.GLACE);
			craquee = false;
		}

		@Override
		public void interaction(Sprite sprite, GameController jeu) {
			if(!craquee){
				craquee=true;
				render.setTexture(TextureCase.GLACE, 1);
			}
			else {
				/* TODO recuperer le jeu et reload le niveau, LE JOUEUR EST MORT!!!! */
			}
			super.interaction(sprite, jeu);
		}
}
