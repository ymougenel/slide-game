package jeu.slide;

import java.io.IOException;

import jeu.noyau.Boutton;
import jeu.noyau.Menu;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;

public class MenuPauseSlide extends Menu {
	
	public MenuPauseSlide() {
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
		
		bouttons.add( new Boutton(texBoutton, null, "reprendre",font));
		bouttons.add( new Boutton(texBoutton, NewEventGame.RESTART, "rejouer",font));
		bouttons.add( new Boutton(texBoutton, NewEventGame.CHARGER_MENU_PRINCIPALE, "menu Principal",font));
		bouttons.add( new Boutton(texBoutton, null, "Details plateau",font));
		bouttons.add( new Boutton(texBoutton, NewEventGame.QUITTER, "Quitter",font));
		
		fond = new Sprite(texFond);
		for(int i=0;i<bouttons.size(); i++){
			bouttons.get(i).setPosition(20,20+100*i);
		}
		
		bouttons.get(0).setColor(Color.GREEN);
	}
}
