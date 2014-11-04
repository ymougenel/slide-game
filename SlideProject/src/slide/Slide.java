package slide;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import jeu.Jeu;
import jeu.Sequence;

import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public class Slide extends Jeu {
	private Sequence plateauCourant;
	private Sequence menuPause;
	
	public Slide(String nom) {
		super(nom);
<<<<<<< HEAD
		// TODO Auto-generated constructor stub
		ObjectInputStream caMarche;
		try {
			caMarche = new ObjectInputStream( new FileInputStream("terrains/terrain2.plt"));
=======
		this.menuPause = new MenuPauseSlide();
		try (ObjectInputStream caMarche = new ObjectInputStream(getClass().getResourceAsStream("/ressources/plateaux/terrain0.plt"))){
>>>>>>> d45eb23c5a5d0f9ab26f7eaeb04b26394dd29922
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
		if(event instanceof KeyEvent && event.type.equals(Event.Type.KEY_PRESSED)){
			switch (((KeyEvent) event).key) {
			case RETURN:
			case A:if(menuPause.isPause()){
						this.plateauCourant.setPause(true);
						this.menuPause.setPause(false);
						this.menuPause.setVisible(true);
						charger(menuPause);
					}else{
						this.plateauCourant.setPause(false);
						this.menuPause.setPause(true);
						this.menuPause.setVisible(false);
						liberer(menuPause);
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
				case CHARGERNIVEAU:
				try (ObjectInputStream caMarche = new ObjectInputStream(getClass().getResourceAsStream("/ressources/plateaux/"+NewEventGame.CHARGERNIVEAU.getMessage()))) {
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
	}
}
