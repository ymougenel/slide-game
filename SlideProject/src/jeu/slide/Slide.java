package jeu.slide;

import java.io.IOException;

import jeu.noyau.Jeu;
import jeu.noyau.Sequence;

import org.jsfml.graphics.TextureCreationException;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public class Slide extends Jeu {
	private Sequence menuPause;
	
	public Slide(String nom) {
		super(nom);
		this.menuPause = new MenuPauseSlide();
		try {
			charger(Plateau.chargerPlateau(0,new Joueur()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void processEvent(Event event) {
		if(event instanceof KeyEvent && event.type.equals(Event.Type.KEY_PRESSED)){
			switch (((KeyEvent) event).key) {
			case RETURN:
			case A:if(!isPause()){
						setPause(true);
						charger(menuPause);
						this.events.remove(event);
					}
					break;
			default:
			}
		}

	}
	
	@Override
	protected void processEventGame(EventGame event) {
		// TODO Auto-generated method stub
		if(event instanceof NewEventGame){
			switch( (NewEventGame) event ){
				case COUCOU:System.out.println("yes");
				default:
			}
		}
		if(event == NewEventGame.COUCOU){
			System.out.println("non");
		}
	}

	public static void main(String[] args) throws IOException, TextureCreationException {
		Slide sl = new Slide("jeu du Slide");
		sl.run();
	}
}