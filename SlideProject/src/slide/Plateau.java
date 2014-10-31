package slide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import jeu.Jeu;
import jeu.Sequence;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

import slide.cases.Case;

public class Plateau extends Sequence implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7433390095796292630L;
	
	private Case[][] damierCases;
	private Entite[][] damierEntite;
	private Vector2i entiteMobile;
	private Vector2i positionJoueur;
	private Joueur joueur;
	private Vector2i dimensionPlateau;
	private transient View camera;
	private boolean checkMouvement;

	public Plateau(Case[][] cases, Entite[][] entites, Joueur joueur,
			Vector2i positionJoueurInitiale) {
		super();
		
		this.damierCases = cases;
		this.damierEntite = entites;
		this.entiteMobile = null;
		this.dimensionPlateau = new Vector2i(cases.length, cases[0].length);
		this.positionJoueur = positionJoueurInitiale;
		this.joueur = joueur;
		checkMouvement = false;

		this.damierEntite[positionJoueurInitiale.x][positionJoueurInitiale.y] = joueur;
		Vector2i taille = Case.getTailleCase();
		int i, j;
		for (i = 0; i < dimensionPlateau.x; i++) {
			for (j = 0; j < dimensionPlateau.y; j++) {
				/* Traitement des cases du pleateau */
				if (damierEntite[i][j] != null) {
					damierEntite[i][j].setPosition(i * taille.x, j * taille.y);
				}

			}
		}
		// TODO initialiser dimension plat
		camera = new View(new FloatRect(0, 0, 160, 144));
		// initialiser();
	}

	@Override
	public void activeUpdate(Jeu game){
		/* Traitement entrees clavier */
		
		for(Event event : game.getEvents()){
			if(event instanceof KeyEvent && event.type.equals(Event.Type.KEY_PRESSED)){
				switch (((KeyEvent) event).key) {
				case UP:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						System.out.println("entitemobile = "
								+ getEntite(positionJoueur));
						getEntite(positionJoueur).setMouvement(
								new Vector2i(0, -1));
						checkMouvement = true;

					}
					break;
				case RIGHT:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						System.out.println("entitemobile = "
								+ getEntite(positionJoueur));
						getEntite(positionJoueur).setMouvement(
								new Vector2i(1, 0));
						checkMouvement = true;
					}
					break;
				case LEFT:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						System.out.println("entitemobile = "
								+ getEntite(positionJoueur));
						getEntite(positionJoueur).setMouvement(
								new Vector2i(-1, 0));
						checkMouvement = true;

					}
					break;
				case DOWN:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						System.out.println("entitemobile = "
								+ getEntite(positionJoueur));
						getEntite(positionJoueur).setMouvement(
								new Vector2i(0, 1));
						checkMouvement = true;

					}
					break;
				default:
					break;
				}
			}
		}	
		// Patch GITAN 
		if (entiteMobile != null ) {
			Vector2i vect = getEntite(entiteMobile).getMouvement();
			if (vect.x==0 && vect.y ==0) {
				entiteMobile=null;
				checkMouvement = false;
				System.out.println("entitemobile annulée");
			}
		}
		// Fin patch GITATn le reste est ~ propres
		
		if(entiteMobile!=null){
			if ( checkMouvement ) {
				/* Phase1 */
				System.out.println("phase 1");
				System.out.println("entite mobile :" +getEntite(entiteMobile)+" en "+entiteMobile);
				Vector2i nouvellesCoordonees = coordoneesSuivantes() ;
				System.out.println("entitesuivante :"+getEntite(nouvellesCoordonees)+" en "+coordoneesSuivantes());
				System.out.println("-------------------------------------------");
				Entite entiteSuivante = getEntite( nouvellesCoordonees );
				if ( entiteSuivante!= null ){
					System.out.println("entite suivante"+entiteSuivante);
					boolean imobile = getEntite(entiteMobile).transmettreMouvement(entiteSuivante);
					entiteMobile= nouvellesCoordonees;
					checkMouvement = true;
					System.out.println("mouvement transmi : "+imobile);
				}
				else {
					checkMouvement = false;
					deplacerEntiteMobile(nouvellesCoordonees);
					System.out.println("debut du mouvement");
				}
			}
			
			else {
				/* Phase 2 */
				System.out.println("mouvement en cours entite en"+entiteMobile);
				getEntite(entiteMobile).update( Jeu.TIME_PER_FRAME );
				if ( getEntite(entiteMobile).mouvementTermine() ) {
					System.out.println("mouvement termine");
					Vector2i cinetique = getEntite(entiteMobile).getMouvement();
					Vector2i nouvelleCinetique = getCase(entiteMobile).interaction(cinetique, game);
					getEntite(entiteMobile).setMouvement(nouvelleCinetique);
					checkMouvement = true;
				}
			}
			
		}
	}

	
	@Override
	public void render(RenderTarget fenetre) {
		// camera.setViewport(new FloatRect(0.5f, 0.5f, 0.5f, 0.5f));
		fenetre.setView(camera);
		Vector2i taille = Case.getTailleCase();
		int i, j;
		Sprite sprite;
		for (i = 0; i < dimensionPlateau.x; i++) {
			for (j = 0; j < dimensionPlateau.y; j++) {
				/* Traitement des cases du pleateau */
				sprite = damierCases[i][j].getSprite();
				sprite.setPosition(i * taille.x, j * taille.y);
				fenetre.draw(sprite);

				/* Traitement des entites du plateau */
				if (damierEntite[i][j] != null) {
					fenetre.draw(damierEntite[i][j]);
				}
			}
		}
	}

	private Vector2i coordoneesSuivantes() {
		if (entiteMobile == null) {
			return null;
		} else {
			System.out.println("mouvement"+getEntite(entiteMobile).getMouvement());
			if( getEntite(entiteMobile).getMouvement()==Vector2i.ZERO ){
				System.out.println("this shoould not appened");
			}
			
			return Vector2i.add(entiteMobile, getEntite(entiteMobile).getMouvement());
		}

	}

	private Entite getEntite(Vector2i position) {
		return damierEntite[position.x][position.y];
	}

	private void deplacerEntiteMobile(Vector2i position) {
		Entite aDeplacer = damierEntite[entiteMobile.x][entiteMobile.y];
		damierEntite[entiteMobile.x][entiteMobile.y] = null;
		damierEntite[position.x][position.y] = aDeplacer;
		entiteMobile = position;
		if(getEntite(entiteMobile)==joueur){
			positionJoueur = position;
		}
	}
	
	private Case getCase ( Vector2i position){
		return damierCases[position.x][position.y];
	}
	
	private void readObject(final ObjectInputStream in) throws IOException,  ClassNotFoundException {
		in.defaultReadObject();
		this.camera = new View (new FloatRect(0, 0, 160, 144));
	}

	@Override
	public void backgroundUpdate(Jeu game) {
		// TODO Auto-generated method stub
		
		
	}
}
