package slide;

import jeu.EventGame;
import jeu.Jeu;
import jeu.Sequence;

import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;

import terrains.Terrain0;

public class Slide extends Jeu {

	public Slide(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
		Sequence seq = new Terrain0(new Joueur(), new Vector2i(0,5));
		chargerUpdate(seq);
		chargerRender(seq);
		run();
	}

	@Override
	protected void processEvent(Event event) {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void processEventGame(EventGame event) {
		// TODO Auto-generated method stub
		if(event instanceof NewEventGame){
			switch( (NewEventGame) event ){
				case COUCOU:System.out.println("yes");
			}
		}
		if(event == NewEventGame.COUCOU){
			System.out.println("non");
		}
	}

	public static void main(String[] args) {
		new Slide("jeu du Slide");
	}
}
