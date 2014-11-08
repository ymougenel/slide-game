package jeu.slide;

import java.io.IOException;

import jeu.noyau.Jeu;
import jeu.noyau.Sequence;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public class MenuPauseSlide extends Sequence {

	private Sprite fond; 
	private int index;
	private int nbBouttons;
	private Sprite[] bouttons;
	private Text reprendre,rejouer,menuPrincipal;
	
	public MenuPauseSlide() {
		Texture texFond = new Texture();
		Texture texBoutton = new Texture();
		Font police = new Font();
		try {
			police.loadFromStream(Fin.class.getResourceAsStream("/ressources/polices/orangejuice.ttf"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		reprendre = new Text("Reprendre", police, 30);
		rejouer = new Text("Rejouer", police, 30);
		menuPrincipal = new Text("Menu Principal", police, 30);
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
		reprendre.setPosition(150,60);
		rejouer.setPosition(160,160);
		menuPrincipal.setPosition(120,260);
		bouttons[index].setColor(Color.GREEN);
	}
	
	/*private void reprendre(){
		
	}*/

	@Override
	public void activeUpdate(Jeu game) {
		int newIndex=0;
		for(Event event : game.getEvents()){
			if(event instanceof KeyEvent && event.type.equals(Event.Type.KEY_PRESSED)){
				switch (((KeyEvent) event).key) {
				case DOWN:
				case RIGHT:
					newIndex++;
					break;
				case LEFT:
				case UP:
					newIndex--;
					break;
				case RETURN:
					if(index==0){
						game.setPause(false);
						game.liberer(this);
					}else if(index == 1){
						game.ajouterEvenement(NewEventGame.RESTART);
						game.setPause(false);
						game.liberer(this);
					}else if(index == 2){
						game.setPause(false);
						game.liberer(this);
					}
				default:
					break;
				}
			}
		}
		if(newIndex!=0){
			bouttons[index].setColor(Color.WHITE);
			index+=newIndex;
			index = (index<0) ? index+nbBouttons :
				(index>=nbBouttons) ? index-nbBouttons: index;
			bouttons[index].setColor(Color.GREEN);
		}
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
		fenetre.draw(menuPrincipal);
		fenetre.draw(rejouer);
		fenetre.draw(reprendre);
	}
}
