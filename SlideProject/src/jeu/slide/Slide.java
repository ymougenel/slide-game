package jeu.slide;

import java.io.IOException;

import jeu.noyau.Jeu;
import jeu.noyau.Sequence;

import org.jsfml.graphics.TextureCreationException;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class Slide extends Jeu {
	private Sequence menuPause;
	private Sequence menuPrincipale;
	private final int debut=-1; /* TODO************** Premier niveau *************************************************/
	
	public enum Etats {
		MENU_PRINCIPAL,
		MENU_PAUSE,
		PLATEAU;
	}
	private Etats etat;
	
	public Slide(String nom) {
		super(nom);
		this.menuPause = new MenuPauseSlide();
		this.menuPrincipale = new MenuPrincipale();
		this.charger(menuPrincipale);
		
		this.etat = Etats.MENU_PRINCIPAL;
	}

	@Override
	protected void processEvent(Event event) {
	}
	
	@Override
	protected void processEventGame(EventGame event) {
		if(event instanceof NewEventGame){
			switch( (NewEventGame) event ){
				case DEBUTPARTIE:		
					try {
						charger(Plateau.chargerPlateau(debut,new Joueur()));
					} catch (IOException e) {				
						e.printStackTrace();
					}
				break;
				case CHARGER_MENU_PAUSE :
					this.charger(menuPause);
				break;
				case CHARGER_MENU_PRINCIPALE :
					this.sequencesChargees.clear();
					this.charger(menuPrincipale);
				break;
				case QUITTER :
					this.fermer();
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