package slide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Paths;

import jeu.Entite;
import jeu.Jeu;
import jeu.Sequence;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

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
	private boolean optionDebug;
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
		optionDebug = false;
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

	private void initialiser() {
		Texture bg = new Texture();
		Image imageSprites = new Image();

		try {
			bg.loadFromFile(Paths.get("src/sprites/bg.png"));
			imageSprites.loadFromFile(Paths.get("src/sprites/pong.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		imageSprites.createMaskFromColor(Color.WHITE);

		Texture textureBall = new Texture();
		Texture textureBarre1 = new Texture();

		try {
			textureBall.loadFromImage(imageSprites, new IntRect(28, 0, 29, 12));
			textureBarre1
					.loadFromImage(imageSprites, new IntRect(0, 0, 12, 45));
		} catch (TextureCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update(Jeu game){
		/* Traitement entrees clavier */
		
		for(Event event : game.getEvents()){
			if(event instanceof KeyEvent){
				switch (((KeyEvent) event).key) {
				case UP:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						System.out.println("entitemobile = "
								+ getEntite(positionJoueur));
						getEntite(positionJoueur).setMouvement(
								new Vector2f(0, -1));
						checkMouvement = true;

					}
					break;
				case RIGHT:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						System.out.println("entitemobile = "
								+ getEntite(positionJoueur));
						getEntite(positionJoueur).setMouvement(
								new Vector2f(1, 0));
						checkMouvement = true;
					}
					break;
				case LEFT:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						System.out.println("entitemobile = "
								+ getEntite(positionJoueur));
						getEntite(positionJoueur).setMouvement(
								new Vector2f(-1, 0));
						checkMouvement = true;

					}
					break;
				case DOWN:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						System.out.println("entitemobile = "
								+ getEntite(positionJoueur));
						getEntite(positionJoueur).setMouvement(
								new Vector2f(0, 1));
						checkMouvement = true;

					}
					break;
				case A:
					optionDebug = true;
					break;
				default:
					break;
				}
			}
		}	
		// Patch GITAN 
		if (entiteMobile != null ) {
			Vector2f vect = getEntite(entiteMobile).getMouvement();
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
				Vector2i nouvellesCoordonees = new Vector2i (coordoneesSuivantes()) ;
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
				getEntite(entiteMobile).update( game.TIME_PER_FRAME );
				if ( getEntite(entiteMobile).mouvementTermine() ) {
					System.out.println("mouvement termine");
					Vector2f cinetique = getEntite(entiteMobile).getMouvement();
					Vector2f nouvelleCinetique = getCase(entiteMobile).interaction(cinetique);
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

	private Vector2f coordoneesSuivantes() {
		if (entiteMobile == null) {
			return null;
		} else {
			System.out.println("mouvement"+getEntite(entiteMobile).getMouvement());
			if( getEntite(entiteMobile).getMouvement()==Vector2f.ZERO ){
				System.out.println("this shoould not appened");
			}
			
			return Vector2f.add( new Vector2f(entiteMobile), getEntite(entiteMobile).getMouvement());
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
}
