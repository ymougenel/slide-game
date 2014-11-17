package jeu.noyau;

import java.util.ArrayList;

import jeu.noyau.Jeu.EventGame;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public abstract class Menu extends Sequence {

	protected Sprite fond; 
	protected int index;
	protected ArrayList<Boutton> bouttons;
	protected Font font;
	
	public Menu() {
		index=0;
		bouttons = new ArrayList<Boutton>();
		font = ChargeurFont.Orange.getFont();
	}

	@Override
	protected void render(RenderTarget fenetre) {
		fenetre.draw(fond);
		for( Boutton boutton : bouttons){
			fenetre.draw(boutton);
		}
	}
	
	protected void ajouterBoutton( Boutton boutton){
		this.bouttons.add(boutton);
	}
	
	public void setFond ( Texture texture) {
		this.fond.setTexture(texture);
	}

	@Override
	protected void processActiveEvent(Jeu game) {
		int newIndex=0;
		for (Event event : game.getEvents()) {
			if (event instanceof KeyEvent
					&& event.type.equals(Event.Type.KEY_PRESSED)) {
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
					game.liberer(this);
					EventGame evt = bouttons.get(index).getEventGame();
					if (evt != null) {
						game.ajouterEvenement(evt);
					}
				default:
					break;
				}
			}
		}
		if(newIndex!=0){
			bouttons.get(index).setColor(Color.WHITE); 
			index+=newIndex;
			index = (index<0) ? index+bouttons.size() :
				(index>=bouttons.size()) ? index-bouttons.size(): index;
			bouttons.get(index).setColor(Color.GREEN);
		}
	}
	@Override
	public void backgroundUpdate(Jeu game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processActiveEventGame(Jeu game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void activeUpdate(Jeu game) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void processBackgroundEvent(Jeu game) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void processBackgroundEventGame(Jeu game) {
		// TODO Auto-generated method stub
		
	}
}
