package jeu.slide;

import java.io.IOException;

import jeu.noyau.Boutton;
import jeu.noyau.Jeu;
import jeu.noyau.Menu;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;

public class MenuPrincipale extends Menu{

	public MenuPrincipale() {
		super();
		Texture texFond = new Texture();
		Texture texBoutton = new Texture();
		try {
			texFond.loadFromStream(getClass().getResourceAsStream("/ressources/images/menu_background.png"));
			Image img = new Image();
			img.loadFromStream(getClass().getResourceAsStream("/ressources/images/boutton.png"));
			img.createMaskFromColor(Color.WHITE);
			texBoutton.loadFromImage(img);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TextureCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bouttons.add( new Boutton(texBoutton, NewEventGame.DEBUTPARTIE, "Nouvelle Partie",font));
		bouttons.add( new Boutton(texBoutton, null, "Options",font));
		bouttons.add( new Boutton(texBoutton, null, "A propos",font));
		bouttons.add( new Boutton(texBoutton, null, "Quitter",font));
		
		fond = new Sprite(texFond);
		for(int i=0;i<bouttons.size(); i++){
			bouttons.get(i).setPosition(20,20+100*i);
		}
		
		bouttons.get(0).setColor(Color.GREEN);
	}
	@Override
	public void backgroundUpdate(Jeu game) {
		// TODO Auto-generated method stub
		
	}



}
