package jeu.slide;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JOptionPane;

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
import jeu.slide.cases.Ice;
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
	private View camera;
	private Map<Vector2i, String> arrivees;
	private String nom;
	private int decompteur; //Affichage de la transition initiale
	private Text texteDebut;
	private FicheTechniquePlateau fichePlateau;

	
	public static Plateau chargerPlateau(String plateau,Joueur joueur) throws IOException{
		FicheTechniquePlateau fiche = new FicheTechniquePlateau();
		Plateau chargee = new Plateau();
		chargee.arrivees = new HashMap<Vector2i, String>();
		chargee.nom = plateau;
		
		BufferedReader chargeur = new BufferedReader(new InputStreamReader(
				Plateau.class.getResourceAsStream("/ressources/plateaux/"+plateau+".plt")));
		final String VIDE = "--";
		String ligne;
		int tx = Integer.parseInt(chargeur.readLine());
		int ty = Integer.parseInt(chargeur.readLine());
		int px = Integer.parseInt(chargeur.readLine());
		int py = Integer.parseInt(chargeur.readLine());
		String dir = chargeur.readLine();
		
		chargee.damierEntite = new Entite[tx+2][ty+2];
		chargee.damierCases = new Case[tx+2][ty+2];
		LinkedList<String> file = new LinkedList<String>();
		while(!(ligne = chargeur.readLine()).equals("")){
			file.add(ligne);
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
				case "gc":chargee.damierCases[j+1][i+1]=new Ice();break;
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
		chargeur.readLine();
		ligne = chargeur.readLine();
		String mot;
		while ( ligne!=" " && ligne != null ){
			mot= ligne.substring(0,ligne.indexOf(" "));
			System.out.println("mot trovue"+mot);
			switch (mot){
			case "@Nom": fiche.nom = ligne.substring(ligne.indexOf(" ")+1);
			break;
			case "@Numero" : fiche.numero =Integer.parseInt( ligne.substring(ligne.indexOf(" ")+1) );
			break;
			case "@Date" : fiche.date = ligne.substring(ligne.indexOf(" ")+1);
			break;
			case "@Author": fiche.auteur = ligne.substring(ligne.indexOf(" ")+1);
			break;
			default: System.out.println("Option non trouve pour" + mot);
			break;
			}
			ligne = chargeur.readLine();
		}
		
		 
		
		
		fiche.afficher();
		
		Direction direction = Enum.valueOf(Direction.class, dir);
		joueur.setElement(direction);
		
		chargeur.close();
		chargee.entiteMobile = null;
		chargee.positionJoueur = new Vector2i(px,py);
		chargee.joueur = joueur;
		chargee.damierEntite[px][py]=joueur;
		joueur.setPosition(px*Case.TAILLECASE.y,py*Case.TAILLECASE.y);
		chargee.camera = new View(new FloatRect(-8, -8, 16*(tx+2), 16*(ty+2)));
		chargee.decompteur = 128;
		String texte = (fiche.nom!=null)?fiche.nom:"Niveau"+plateau ;
		chargee.texteDebut = new Text(texte, ChargeurFont.Stocky.getFont(),80);
		chargee.texteDebut.setColor(Color.BLUE);
		chargee.texteDebut.setPosition(10,10);
		chargee.fichePlateau = fiche;
		return chargee;
	}
	
	private Plateau(){
		super();
	}
	
	@Override
	protected void processActiveEvent(Jeu game){
		Direction sens=null;
		for(Event event : game.getEvents()){
			if(event instanceof KeyEvent && event.type.equals(Event.Type.KEY_PRESSED)){
				switch (((KeyEvent) event).key) {
				case UP:
						sens=Direction.HAUT;
					break;
				case RIGHT:
						sens=Direction.DROITE;
					break;
				case LEFT:
						sens=Direction.GAUCHE;
					break;
				case DOWN:
						sens=Direction.BAS;
					break;
					case M:
					case RETURN:
					case A:
						game.pause();
					break;
					case R:
						game.ajouterEvenement( NewEventGame.RESTART );
					break;
					case Q:
						game.fermer();
					break;
					case NUMPAD5:
					String[] listePlateau;
					try {
						listePlateau = new File(Plateau.class.getResource("/ressources/plateaux").toURI()).list();
						String plateauACharger = (String)JOptionPane.showInputDialog(null,"Niveau a charger :",
								"Mode cheat activated !",JOptionPane.QUESTION_MESSAGE,null,listePlateau,null);
						if (plateauACharger!=null){
							plateauACharger = plateauACharger.substring(0, plateauACharger.length()-4);
							game.charger(Plateau.chargerPlateau(plateauACharger, joueur));
							game.liberer(this);
						}
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
					
					case NUMPAD4:
					/*try {
						game.charger(Plateau.chargerPlateau(niveau-1, joueur));
						game.liberer(this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					break;					
				default:
					break;
				}
			}
		}
		if(sens!=null && entiteMobile == null) {
			entiteMobile = positionJoueur;
			joueur.setElement(sens);
			joueur.setMouvement(sens.getSens());
		}
	}
	
	@Override
	protected void processActiveEventGame(Jeu game){
		for(EventGame eg : game.getEventsGame()){
			switch((NewEventGame)eg){
			case COUCOU:break;
			case RESTART:
				try {
					game.charger(Plateau.chargerPlateau(nom, joueur));
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
		
	}
	
	@Override
	protected void processBackgroundEventGame(Jeu game) {
		
	}
	
	@Override
	protected void activeUpdate(Jeu game){		
		if(entiteMobile!=null){
			Entite mobile=damierEntite[entiteMobile.x][entiteMobile.y];
			if (mobile.debutMouvement) {
				/* Phase1 */
				Vector2i nouvellesCoordonees = Vector2i.add(entiteMobile, mobile.getMouvement());
				Entite entiteSuivante =damierEntite[nouvellesCoordonees.x][nouvellesCoordonees.y];
				if ( entiteSuivante!= null && !entiteSuivante.isFantome()){
					entiteSuivante.setMouvement( mobile.getMouvement());
					mobile.setMouvement( Vector2i.ZERO);
					entiteMobile= nouvellesCoordonees;
				} else {
					damierCases[nouvellesCoordonees.x][nouvellesCoordonees.y].collision(mobile);
					if (mobile.getMouvement().equals(Vector2i.ZERO)) {
						entiteMobile=null;
					}else{
						damierEntite[entiteMobile.x][entiteMobile.y] = null;
						damierEntite[nouvellesCoordonees.x][nouvellesCoordonees.y] = mobile;
						entiteMobile = nouvellesCoordonees;
						if(mobile==joueur){
							positionJoueur = nouvellesCoordonees;
						}
					}
					mobile.update();
				}
			}else if ( mobile.mouvementTermine() ) {
				/* Phase 2 */
				Case destination = damierCases[entiteMobile.x][entiteMobile.y];
				mobile.setMouvement(destination.interaction(mobile.getMouvement(), game));
				if(destination == Arrivee.getInstance() && mobile == joueur){
					changerNiveau(game);
				}
				if (mobile.getMouvement().equals(Vector2i.ZERO)) {
					entiteMobile=null;
				}
			}else{
				mobile.update();
			}
		}
	}

	private void changerNiveau(Jeu game){
		try {
			String nouveauNiveau = arrivees.get(entiteMobile);
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
	
	@Override
	protected void render(RenderTarget fenetre) {
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
			fenetre.setView(fenetre.getDefaultView());
			decompteur--;
			if (4*decompteur <256){
				texteDebut.setColor(new Color(Color.BLUE, 4*decompteur));
			}
			fenetre.draw(texteDebut);
		}
	}

	@Override
	protected void backgroundUpdate(Jeu game) {
	
	}
}
