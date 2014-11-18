package jeu.slide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import jeu.noyau.ChargeurFont;
import jeu.noyau.Direction;
import jeu.noyau.Jeu;
import jeu.noyau.Jeu.EventGame;
import jeu.noyau.Sequence;
import jeu.slide.Entite.TextureEntite;
import jeu.slide.cases.Arrivee;
import jeu.slide.cases.Case;
import jeu.slide.cases.Fleche;
import jeu.slide.cases.Glace;
import jeu.slide.cases.Porte;
import jeu.slide.cases.Rocher;
import jeu.slide.cases.Sol;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public class Plateau extends Sequence {
	
	private Case[][] damierCases;
	private Entite[][] damierEntite;
	private Vector2i entiteMobile;
	private Vector2i positionJoueur;
	private Joueur joueur;
	//private Vector2i dimensionPlateau;
	private View camera;
	private boolean checkMouvement;
	private Map<Vector2i, Integer> arrivees;
	private int niveau;
	private int decompteur; //Affichage de la transition initiale
	private Text texteDebut;

	
	public static Plateau chargerPlateau(int numero,Joueur joueur) throws IOException{
		Plateau chargee = new Plateau();
		chargee.arrivees = new HashMap<Vector2i, Integer>();
		chargee.niveau = numero;
		
		BufferedReader chargeur = new BufferedReader(new InputStreamReader(
				Plateau.class.getResourceAsStream("/ressources/plateaux/terrain"+numero+".ascii")));
		final String VIDE = "--";
		String ligne;
		int tx = Integer.parseInt(chargeur.readLine());
		int ty = Integer.parseInt(chargeur.readLine());
		int px = Integer.parseInt(chargeur.readLine());
		int py = Integer.parseInt(chargeur.readLine());
		String dir = chargeur.readLine();
		
		chargee.damierEntite = new Entite[tx+2][ty+2];
		chargee.damierCases = new Case[tx+2][ty+2];
		LinkedList<Integer> file = new LinkedList<Integer>();
		while(!(ligne = chargeur.readLine()).equals("")){
			file.add(Integer.parseInt(ligne));
		}
		
		for(int i=0;i<ty;i++){
			ligne = chargeur.readLine();
			for(int j=0;j<tx;j++){
				switch(ligne.substring(2*j, 2*(j+1))){
				case "gl":chargee.damierCases[j+1][i+1]=Glace.getInstance();break;
				case "tr":chargee.damierCases[j+1][i+1]=Sol.getInstance();break;
				case "fh":chargee.damierCases[j+1][i+1]=Fleche.getInstance(Direction.HAUT);break;
				case "fg":chargee.damierCases[j+1][i+1]=Fleche.getInstance(Direction.GAUCHE);break;
				case "fd":chargee.damierCases[j+1][i+1]=Fleche.getInstance(Direction.DROITE);break;
				case "fb":chargee.damierCases[j+1][i+1]=Fleche.getInstance(Direction.BAS);break;
				case "ar":chargee.damierCases[j+1][i+1]=Arrivee.getInstance();
							chargee.arrivees.put(new Vector2i(j+1,i+1), file.poll()); break;
				case "ri":chargee.damierCases[j+1][i+1]=Rocher.getInstance();break;
				case "po":chargee.damierCases[j+1][i+1]=new Porte();break;
				}
			}				
			chargee.damierCases[tx+1][i+1] = Rocher.getInstance();			
			chargee.damierCases[0][i+1] = Rocher.getInstance();	
		}
		
		for (int i=0; i<tx; i++) {
			chargee.damierCases[i+1][0] = Rocher.getInstance();
			chargee.damierCases[i+1][ty+1] = Rocher.getInstance();
		}
		chargee.damierCases[0][0] = Rocher.getInstance();	
		chargee.damierCases[tx+1][0] = Rocher.getInstance();	
		chargee.damierCases[0][ty+1] = Rocher.getInstance();	
		chargee.damierCases[tx+1][ty+1] = Rocher.getInstance();
		
		chargeur.readLine();
		for(int i=0;i<ty;i++){
			ligne = chargeur.readLine();
			for(int j=0;j<tx;j++){
				String code = ligne.substring(2*j, 2*j+2);
				if(!code.equals(VIDE)){
					switch(code){
					case "rm":chargee.damierEntite[j+1][i+1]=new Entite(TextureEntite.ROCHERMOBILE);break;
					case "cl":chargee.damierEntite[j+1][i+1]=new Entite(TextureEntite.CLE);break;
					default:break;
					}
					chargee.damierEntite[j+1][i+1].setPosition((j+1)*Case.TAILLECASE.x,(i+1)*Case.TAILLECASE.y);
				}
			}
		}
		Direction direction = Enum.valueOf(Direction.class, dir);
		joueur.setElement(direction);
		
		chargeur.close();
		chargee.entiteMobile = null;
		chargee.positionJoueur = new Vector2i(px,py);
		chargee.joueur = joueur;
		chargee.damierEntite[px][py]=joueur;
		joueur.setPosition(px*Case.TAILLECASE.y,py*Case.TAILLECASE.y);
		chargee.checkMouvement = false;
		chargee.camera = new View(new FloatRect(-8, -8, 16*(tx+2), 16*(ty+2)));
		chargee.decompteur = 128;
		chargee.texteDebut = new Text("Niveau"+numero, ChargeurFont.Orange.getFont(),20);
		chargee.texteDebut.setColor(Color.RED);
		return chargee;
	}
	
	private Plateau(){
		super();
	}
	
	@Override
	protected void processActiveEvent(Jeu game){
		for(Event event : game.getEvents()){
			if(event instanceof KeyEvent && event.type.equals(Event.Type.KEY_PRESSED)){
				switch (((KeyEvent) event).key) {
				case UP:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						joueur.setElement(Direction.HAUT);
						getEntite(positionJoueur).setMouvement(Direction.HAUT.getSens());
						checkMouvement = true;

					}
					break;
				case RIGHT:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						joueur.setElement(Direction.DROITE);
						getEntite(positionJoueur).setMouvement(Direction.DROITE.getSens());
						checkMouvement = true;
					}
					break;
				case LEFT:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						joueur.setElement(Direction.GAUCHE);
						getEntite(positionJoueur).setMouvement(Direction.GAUCHE.getSens());
						checkMouvement = true;

					}
					break;
				case DOWN:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						joueur.setElement(Direction.BAS);
						getEntite(positionJoueur).setMouvement(Direction.BAS.getSens());
						checkMouvement = true;

					}
					break;
					case RETURN:
					case A:
						game.pause();
					break;
				default:
					break;
				}
			}
		}		
	}
	
	@Override
	protected void processActiveEventGame(Jeu game){
		for(EventGame eg : game.getEventsGame()){
			switch((NewEventGame)eg){
			case COUCOU:break;
			case RESTART:
				try {
					game.charger(Plateau.chargerPlateau(niveau, joueur));
					game.liberer(this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
	}

	@Override
	protected void processBackgroundEvent(Jeu game) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void processBackgroundEventGame(Jeu game) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void activeUpdate(Jeu game){
		// Patch GITAN 
		if (entiteMobile != null ) {
			Vector2i vect = getEntite(entiteMobile).getMouvement();
			if (vect.x==0 && vect.y ==0) {
				entiteMobile=null;
				checkMouvement = false;
			}
		}
		// Fin patch GITATn le reste est ~ propres
		
		if(entiteMobile!=null){
			if ( checkMouvement ) {
				/* Phase1 */
				Vector2i nouvellesCoordonees = coordoneesSuivantes() ;
				Entite entiteSuivante = getEntite( nouvellesCoordonees );
				if ( entiteSuivante!= null && !entiteSuivante.isFantome()){
					//getEntite(entiteMobile).transmettreMouvement(entiteSuivante);
					entiteSuivante.collision( getEntite(entiteMobile));
					entiteMobile= nouvellesCoordonees;
					checkMouvement = true;
				} else {
					damierCases[nouvellesCoordonees.x][nouvellesCoordonees.y].collision(getEntite(entiteMobile));
					if (! getEntite(entiteMobile).getMouvement().equals(Vector2i.ZERO)) {
						deplacerEntiteMobile(nouvellesCoordonees);
						checkMouvement = false;					
					}	
				}
				

			}else {
				/* Phase 2 */
				getEntite(entiteMobile).update();
				getEntite(entiteMobile).animer();
				if ( getEntite(entiteMobile).mouvementTermine() ) {
					Vector2i nouvelleCinetique = getCase(entiteMobile).interaction(getEntite(entiteMobile).getMouvement(), game);
					getEntite(entiteMobile).setMouvement(nouvelleCinetique);
					checkMouvement = true;
					if(getCase(entiteMobile) == Arrivee.getInstance() && getEntite(entiteMobile) == joueur){
						try {
							Integer nouveauNiveau = arrivees.get(entiteMobile);
							if(nouveauNiveau != null){
								game.charger(Plateau.chargerPlateau(nouveauNiveau, joueur));
							}else{
								game.charger(new Fin());
							}
							game.liberer(this);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			
		}
	}

	
	@Override
	public void render(RenderTarget fenetre) {
		fenetre.setView(camera);
		int i, j;
		Sprite sprite;
		for (i = 0; i < damierCases.length; i++) {
			for (j = 0; j < damierCases[0].length; j++) {
				/* Traitement des cases du pleateau */
				sprite = damierCases[i][j].getSprite();
				sprite.setPosition(i * Case.TAILLECASE.x, j * Case.TAILLECASE.y);
				fenetre.draw(sprite);
			}
		}
		for (i = 0; i < damierEntite.length; i++) {
			for (j = 0; j < damierEntite[0].length; j++) {
				/* Traitement des entites du plateau */
				if (damierEntite[i][j] != null) {
					fenetre.draw(damierEntite[i][j]);
				}
			}
		}
		
		if (decompteur>0 ){
			decompteur--;
			if (4*decompteur <256){
				texteDebut.setColor(new Color(Color.RED, 4*decompteur));
			}
			fenetre.draw(texteDebut);
		}
	}

	private Vector2i coordoneesSuivantes() {
		if (entiteMobile == null) {
			return null;
		} else {
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

	@Override
	public void backgroundUpdate(Jeu game) {
		// TODO Auto-generated method stub
		
		
	}
}
