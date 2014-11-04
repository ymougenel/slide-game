package slide;

import java.io.IOException;

import jeu.Jeu;
import jeu.Sequence;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public class MenuPauseSlide extends Sequence {

	private Sprite fond; 
	private int index;
	private int nbBouttons;
	private Sprite[] bouttons;
	
	public MenuPauseSlide() {
		Texture texFond = new Texture();
		Texture texBoutton = new Texture();
		index=0;
		nbBouttons =3;
		bouttons = new Sprite[nbBouttons];
		
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
		fond = new Sprite(texFond);
		for(int i=0;i<nbBouttons;i++){
			bouttons[i] = new Sprite(texBoutton);
			bouttons[i].setPosition(20,20+100*i);
		}
		bouttons[index].setColor(Color.GREEN);
	}
	
	private void reprendre(){
		
	}

	@Override
	public void activeUpdate(Jeu game) {
		
		for(Event event : game.getEvents()){
			if(event instanceof KeyEvent && event.type.equals(Event.Type.KEY_PRESSED)){
				switch (((KeyEvent) event).key) {
				case UP:
					index--;
					break;
				case RIGHT:
					index++;
					break;
				case LEFT:
					index--;
					break;
				case DOWN:
					index++;
					break;
				default:
					break;
				}
			}
		}
		index = (index<0) ? index+nbBouttons :
				(index>=nbBouttons) ? index-nbBouttons: index;
		bouttons[index].setColor(Color.GREEN);
	}

	@Override
	public void backgroundUpdate(Jeu game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void render(RenderTarget fenetre) {
		fenetre.draw(fond);
		for(int i=0;i<nbBouttons;i++){
			fenetre.draw(bouttons[i]);
		}
		
	}
}
