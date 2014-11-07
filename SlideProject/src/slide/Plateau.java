package slide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import jeu.Jeu;
import jeu.Sequence;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

import slide.Entite.TextureEntite;
import slide.cases.Arrivee;
import slide.cases.Case;
import slide.cases.Fleche;
import slide.cases.Fleche.Sens;
import slide.cases.Glace;
import slide.cases.Rocher;
import slide.cases.Sol;

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
	//private Vector2i dimensionPlateau;
	private transient View camera;
	private boolean checkMouvement;
	private Map<Vector2i, Integer> arrivees;

	
	public static Plateau chargerPlateau(int numero,Joueur joueur) throws IOException{
		Plateau chargee = new Plateau();
		chargee.arrivees = new HashMap<Vector2i, Integer>();
		
		BufferedReader chargeur = new BufferedReader(new InputStreamReader(
				Plateau.class.getResourceAsStream("/ressources/plateaux/terrain"+numero+".ascii")));
		final String VIDE = "--";
		String ligne;
		int tx = Integer.parseInt(chargeur.readLine());
		int ty = Integer.parseInt(chargeur.readLine());
		int px = Integer.parseInt(chargeur.readLine());
		int py = Integer.parseInt(chargeur.readLine());
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
				case "fh":chargee.damierCases[j+1][i+1]=Fleche.getInstance(Sens.HAUT);break;
				case "fg":chargee.damierCases[j+1][i+1]=Fleche.getInstance(Sens.GAUCHE);break;
				case "fd":chargee.damierCases[j+1][i+1]=Fleche.getInstance(Sens.DROITE);break;
				case "fb":chargee.damierCases[j+1][i+1]=Fleche.getInstance(Sens.BAS);break;
				case "ar":chargee.damierCases[j+1][i+1]=Arrivee.getInstance();
							chargee.arrivees.put(new Vector2i(j+1,i+1), file.poll()); break;
				case "ri":chargee.damierCases[j+1][i+1]=Rocher.getInstance();break;
				}
			}
			chargee.damierCases[i+1][0] = Rocher.getInstance();	
			chargee.damierCases[tx+1][i+1] = Rocher.getInstance();	
			chargee.damierCases[i+1][ty+1] = Rocher.getInstance();	
			chargee.damierCases[0][i+1] = Rocher.getInstance();	
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
					case "rm":chargee.damierEntite[j+1][i+1]=new Entite(TextureEntite.ROCHERMOBILE, true);break;
					default:break;
					}
					chargee.damierEntite[j+1][i+1].setPosition((j+1)*Case.TAILLECASE.x,(i+1)*Case.TAILLECASE.y);
				}
			}
		}	
		chargeur.close();
		chargee.entiteMobile = null;
		chargee.positionJoueur = new Vector2i(px,py);
		chargee.joueur = joueur;
		chargee.damierEntite[px][py]=joueur;
		joueur.setPosition(px*Case.TAILLECASE.y,py*Case.TAILLECASE.y);
		chargee.checkMouvement = false;
		chargee.camera = new View(new FloatRect(-8, -8, 16*(tx+2), 16*(ty+2)));
		return chargee;
	}
	
	private Plateau(){
		super();
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
						getEntite(positionJoueur).setMouvement(
								new Vector2i(0, -1));
						checkMouvement = true;

					}
					break;
				case RIGHT:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						getEntite(positionJoueur).setMouvement(
								new Vector2i(1, 0));
						checkMouvement = true;
					}
					break;
				case LEFT:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
						getEntite(positionJoueur).setMouvement(
								new Vector2i(-1, 0));
						checkMouvement = true;

					}
					break;
				case DOWN:
					if (entiteMobile == null) {
						entiteMobile = positionJoueur;
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
			}
		}
		// Fin patch GITATn le reste est ~ propres
		
		if(entiteMobile!=null){
			if ( checkMouvement ) {
				/* Phase1 */
				Vector2i nouvellesCoordonees = coordoneesSuivantes() ;
				Entite entiteSuivante = getEntite( nouvellesCoordonees );
				if ( entiteSuivante!= null ){
					getEntite(entiteMobile).transmettreMouvement(entiteSuivante);
					entiteMobile= nouvellesCoordonees;
					checkMouvement = true;
				}else if (damierCases[nouvellesCoordonees.x][nouvellesCoordonees.y] == Rocher.getInstance()){
					getEntite(entiteMobile).setMouvement(Vector2i.ZERO);
				}else {
					deplacerEntiteMobile(nouvellesCoordonees);
					checkMouvement = false;
				}
			}else {
				/* Phase 2 */
				getEntite(entiteMobile).update();
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
				if (damierEntite[j][i] != null) {
					fenetre.draw(damierEntite[j][i]);
				}
			}
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
	
	private void readObject(ObjectInputStream ois) throws IOException,ClassNotFoundException{
		ois.defaultReadObject();
		camera = new View(new FloatRect(-8, -8, 16*(damierCases.length+2), 16*(damierCases[0].length+2)));
		
	}
}
