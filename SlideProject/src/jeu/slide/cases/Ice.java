package jeu.slide.cases;

import jeu.noyau.Jeu;

import org.jsfml.system.Vector2i;

public class Ice extends Case{
		
		private boolean craquee;

		public Ice() {
			super(TextureCase.GLACE);
			craquee = false;
		}

		@Override
		public Vector2i interaction(Vector2i vitesse, Jeu jeu) {
			if(!craquee){
				craquee=true;
				chargeur.addTexture(sprite, TextureCase.GLACE, 1);
			}
			else {
				/* TODO recuperer le jeu et reload le niveau, LE JOUEUR EST MORT!!!! */
			}
			return Vector2i.ZERO;
		}
}
