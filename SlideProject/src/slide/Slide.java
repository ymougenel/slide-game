package slide;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import jeu.Jeu;
import jeu.Sequence;

import org.jsfml.graphics.TextureCreationException;
import org.jsfml.window.event.Event;

public class Slide extends Jeu {
	private Sequence plateauCourant;
	
	public Slide(String nom) {
		super(nom);
		try (ObjectInputStream caMarche = new ObjectInputStream(getClass().getResourceAsStream("/plateaux/terrain0.plt"))){
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
		charger(plateauCourant);
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
				case CHARGERNIVEAU:
				try (ObjectInputStream caMarche = new ObjectInputStream(getClass().getResourceAsStream("/plateaux/"+NewEventGame.CHARGERNIVEAU.getMessage()))) {
					this.liberer(plateauCourant);
					plateauCourant = (Sequence)caMarche.readObject();
					this.charger(plateauCourant);
					plateauCourant.setPause(false);
					plateauCourant.setVisible(true);
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

	public static void main(String[] args) throws IOException, TextureCreationException {
		Slide sl = new Slide("jeu du Slide");
		sl.run();
		//Code produisant l'erreur xcb
		/*
		RenderWindow foo = new RenderWindow (new VideoMode(600, 600),"problème xcb");
		Texture bar = new Texture();
		bar.create(600, 600);
		bar = null;
		System.gc();
		foo.display();
		*/
	}
}
