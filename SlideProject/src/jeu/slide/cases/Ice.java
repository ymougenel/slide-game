package jeu.slide.cases;

import jeu.noyau.Jeu;
import jeu.slide.Entite;

import org.jsfml.system.Vector2i;

public class Ice extends Case{
		
		private boolean craquee;

		public Ice() {
			super(TextureCase.GLACE_CRAQUANTE);
			craquee = false;
		}

		@Override
		public Vector2i interaction(Vector2i vitesse, Jeu jeu) {
			return Vector2i.ZERO;
		}
		
		@Override
		public void collision(Entite collisioneur) {
			super.collision(collisioneur);
			if(!craquee){
				craquee=true;
				chargeur.addTexture(sprite, TextureCase.GLACE_CRAQUEE, 0);
			}
			else {
				/* TODO recuperer le jeu et reload le niveau, LE JOUEUR EST MORT!!!! */
			}
		}


}
