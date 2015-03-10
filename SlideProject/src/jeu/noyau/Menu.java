package jeu.noyau;

import java.util.ArrayList;

import jeu.noyau.render.ChargeurFont;
import jeu.noyau.render.Render;
import jeu.noyau.render.ViewController;
import jeu.slide.Slide;
import jeu.slide.jsfml.ViewControllerJSFML;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public abstract class Menu extends Sequence implements Render<Sequence> {

	protected Sprite fond; 
	protected int index;
	protected ArrayList<Boutton> bouttons;
	protected Font font;
	
	public Menu(Slide game,int id) {
		super(game);
		index=0;
		bouttons = new ArrayList<Boutton>();
		font = ChargeurFont.Orange.getFont();
	}
	
	@Override
	public Render<Sequence> getRender() {
		return this;
	}

	@Override
	public void render(ViewController vc, Sequence seq) {
		ViewControllerJSFML view = (ViewControllerJSFML) vc;
		view.getRenderView().draw(fond);
		for( Boutton boutton : bouttons){
			view.getRenderView().draw(boutton);
		}
	}
	
	protected void ajouterBoutton( Boutton boutton){
		this.bouttons.add(boutton);
	}
	
	public void setFond ( Texture texture) {
		this.fond.setTexture(texture);
	}

	
	
	@Override
	public void processInputs(GameController game) {
		Slide slide = (Slide) game;
		int newIndex=0;
		for (Event event : slide.getEvents()) {
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
