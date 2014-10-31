package slide;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import jeu.Jeu;
import jeu.Sequence;

import org.jsfml.window.event.Event;

public class Slide extends Jeu {
	private Sequence plateauCourant;
	
	public Slide(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
		ObjectInputStream caMarche;
		try {
			caMarche = new ObjectInputStream( new FileInputStream("terrains/terrain0.plt"));
			plateauCourant = (Sequence)caMarche.readObject();
			plateauCourant.setPause(false);
			plateauCourant.setVisible(true);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Sequence seq = new Terrain0(new Joueur(), new Vector2i(1,6));
		chargerUpdate(plateauCourant);
		chargerRender(plateauCourant);
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
				case CHARGERNIVEAU: 	ObjectInputStream caMarche;
				
				try {
					this.libererUpdate(plateauCourant);
					this.libererRender(plateauCourant);
					caMarche = new ObjectInputStream( new FileInputStream("terrains/"+NewEventGame.CHARGERNIVEAU.getMessage()));
					plateauCourant = (Sequence)caMarche.readObject();
					this.chargerRender(plateauCourant);
					this.chargerUpdate(plateauCourant);
					plateauCourant.setPause(false);
					plateauCourant.setVisible(true);
					
					caMarche.close();
				} catch (Exception  e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			

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
