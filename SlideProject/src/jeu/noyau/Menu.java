package jeu.noyau;

import java.util.ArrayList;

import jeu.noyau.render.ChargeurFont;
import jeu.slide.Slide;
import jeu.slide.SlideSequence;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public abstract class Menu extends SlideSequence {

	protected Sprite fond; 
	protected int index;
	protected ArrayList<Boutton> bouttons;
	protected Font font;
	
	public Menu(Slide game,int id) {
		super(game,id);
		index=0;
		bouttons = new ArrayList<Boutton>();
		font = ChargeurFont.Orange.getFont();
	}

	@Override
	protected void render() {
		getGame().getRenderView().draw(fond);
		for( Boutton boutton : bouttons){
			getGame().getRenderView().draw(boutton);
		}
	}
	
	protected void ajouterBoutton( Boutton boutton){
		this.bouttons.add(boutton);
	}
	
	public void setFond ( Texture texture) {
		this.fond.setTexture(texture);
	}

	
	
	@Override
	protected void processInputs() {
		int newIndex=0;
		for (Event event : getGame().getEvents()) {
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
					setMode(Mode.Pause);
					performedIndex(index,game);
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
	
	protected abstract void performedIndex(int index,GameController game);
}
