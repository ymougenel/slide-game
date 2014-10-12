package slide;

import java.io.IOException;
import java.nio.file.Paths;

import jeu.Entite;
import jeu.Jeu;
import jeu.Sequence;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Shader;
import org.jsfml.graphics.Shape;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public class Plateau extends Sequence {
	private Case[][] damierCases;
	private Entite[][] damierEntite;
	private Vector2i entiteMobile;
	private Vector2i joueur;
	private Vector2i dimensionPlateau;
	private View camera;
	private static final int EPSILON=0;//TODO choisir Epsilon
	
	public Plateau(Case[][] cases, Entite[][] entites, Joueur joueur, Vector2i positionJoueurInitiale){
		super();
		this.damierCases = cases;
		this.damierEntite = entites;
		this.entiteMobile=null;
		this.dimensionPlateau = new Vector2i(cases.length,cases[0].length);
		this.joueur = positionJoueurInitiale;
		
		this.damierEntite[positionJoueurInitiale.x][positionJoueurInitiale.y] = joueur;
		Vector2i taille= Case.getTailleCase();
		int i,j;
		for (i=0; i<dimensionPlateau.x; i++) {
			for (j=0; j<dimensionPlateau.y; j++) {
				/* Traitement des cases du pleateau */
				if ( damierEntite[i][j] != null ) {
					damierEntite[i][j].setPosition(i*taille.x, j* taille.y );
				}
				
			}
		}
		// TODO initialiser dimension plat
		camera=new View(new FloatRect(0,0,160,144));
		//initialiser();
	}
	
	private void initialiser(){
		Texture bg=new Texture();
		Image imageSprites=new Image();
		
		try {
			bg.loadFromFile( Paths.get("src/sprites/bg.png")   );
			imageSprites.loadFromFile(Paths.get("src/sprites/pong.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		imageSprites.createMaskFromColor(Color.WHITE);
		
		Texture textureBall=new Texture();
		Texture textureBarre1=new Texture();
		
		try {
			textureBall.loadFromImage(imageSprites, new IntRect(28, 0, 29, 12));
			textureBarre1.loadFromImage(imageSprites, new IntRect(0,0,12,45));
		} catch (TextureCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	
	public void update(Jeu game){
		int mouvement=1;
		for(Event event : game.getEvents()){
			if(event instanceof KeyEvent){
				switch( ((KeyEvent)event).key ){
					case UP: if (entiteMobile == null) {
									entiteMobile = joueur;
									damierEntite[joueur.x][joueur.y].setVitesse( new Vector2i(0,-mouvement));
								}
							break;
					case RIGHT: if (entiteMobile == null) {
						entiteMobile = joueur;
						damierEntite[joueur.x][joueur.y].setVitesse( new Vector2i(mouvement,0));
					}					
							break;
					default:break;
				}
			}
		}
		if(entiteMobile!=null){
			Vector2i taille= Case.getTailleCase(); 
			Vector2f positionEntitieMobile = damierEntite[entiteMobile.x][entiteMobile.y].getPosition();
			int distance = (int)(entiteMobile.x*taille.x-positionEntitieMobile.x
								+entiteMobile.y*taille.y-positionEntitieMobile.y);
			Entite mobile = damierEntite[entiteMobile.x][entiteMobile.y];
			if(distance>EPSILON){
				//phase2
				
				mobile.deplacer( game.TIME_PER_FRAME);
				
				 distance = (int)(entiteMobile.x*taille.x-positionEntitieMobile.x
								+entiteMobile.y*taille.y-positionEntitieMobile.y);
				 if(distance <= 0){
					 
					 mobile.setPosition(entiteMobile.x*taille.x, entiteMobile.y*taille.y );
					 Vector2i nouvelleVitesse = damierCases[entiteMobile.x][entiteMobile.y].interaction(mobile.getVitesse());
					 mobile.setVitesse(nouvelleVitesse);
					 
					 if (nouvelleVitesse == Vector2i.ZERO) {
						 System.out.println("THis sould happend"+mobile.getVitesse());
						entiteMobile = null;
					 }
					 else { System.out.println("Jaime le viande");}

				 }
				
				/*
				regarder si on a dépacer (recalculer la distance)
					.Si inferieur à epsilon
						Replacer sur la case (position exact)
						appeller intériaction vitesse de case entité x,y, elle revoie un vitesse
							..si vitesse non nul
								donner la vitesse a entité mobile (setVItesee)
								
					.Sinon mettre entité mobile à nulle
				*/
			}else{
				Vector2i caseSuivante = this.coordoneesSuivantes();
				if(caseSuivante.x == entiteMobile.x && caseSuivante.x == entiteMobile.x){
					
				}
				Entite entiteSuivante = damierEntite[caseSuivante.x][caseSuivante.y];
				if ( entiteSuivante ==null) {
					damierEntite[caseSuivante.x][caseSuivante.y] = damierEntite[entiteMobile.x][entiteMobile.y];
					damierEntite[entiteMobile.x][entiteMobile.y]=null;
					entiteMobile = caseSuivante;
				}
				else {
					boolean vitesseTransmise;
					vitesseTransmise = entiteSuivante.setVitesse(mobile.getVitesse());
					mobile.setVitesse(Vector2i.ZERO);
					entiteSuivante = null;
					if (vitesseTransmise){
						entiteMobile = caseSuivante;
					}	
				}
				//phase1
					/*
					  Regarder si il y a une entité sur la case suivante
					  	.Si il y en a une
					  		transmettre vitesse
					  		Elle devient entité mobile
					  	.Sinon
					  		Deplace entité mobile
					 */
			}
		}
	}

	@Override
	public void render(RenderTarget fenetre) {
		//camera.setViewport(new FloatRect(0.5f, 0.5f, 0.5f, 0.5f));
		fenetre.setView(camera);
		Vector2i taille= Case.getTailleCase();
		if(entiteMobile != null){
			Shape cercle = new CircleShape(5);
			cercle.setPosition(entiteMobile.x*taille.x,entiteMobile.y*taille.y);
			cercle.setFillColor(Color.BLUE);
			fenetre.draw(cercle);
		}
		int i,j;
		Sprite sprite;
		for (i=0; i<dimensionPlateau.x; i++) {
			for (j=0; j<dimensionPlateau.y; j++) {
				/* Traitement des cases du pleateau */
				sprite =damierCases[i][j].getSprite();
				sprite.setPosition(i*taille.x, j* taille.y );
				fenetre.draw(sprite);
				
				/* Traitement des entites du plateau */
				if ( damierEntite[i][j] != null){
					fenetre.draw(damierEntite[i][j]); 
				}
			}
		}
		 
	}
	
	private Vector2i coordoneesSuivantes(){
		if (entiteMobile==null){
			return null;
		}
		else {
			Entite mobile = damierEntite[entiteMobile.x][entiteMobile.y];
			Vector2i vitesse = mobile.getVitesse();
			int positionX = entiteMobile.x; // position de lentite mobile selon x
			int positionY = entiteMobile.y; // position de lentite mobile selon y
			
			if (vitesse.x > 0) { // Deplacement x positif
				positionX++;
			}
			else if ( vitesse.x < 0) {
				positionX--;
			}
			else if (vitesse.y > 0) { // Deplacement x positif
				positionY++;
			}
			else if ( vitesse.y < 0) {
				positionY--;
			}		
			else {
				//System.out.println("THis souldnot happend"+vitesse);
			}
			return new Vector2i(positionX, positionY);
		}
		
	}
}
