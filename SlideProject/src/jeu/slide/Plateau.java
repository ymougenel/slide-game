package jeu.slide;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import jeu.noyau.Direction;
import jeu.noyau.GameController;
import jeu.noyau.GameController.EventGame;
import jeu.noyau.Sequence;
import jeu.noyau.render.ChargeurFont;
import jeu.slide.Sprite.TextureEntite;
import jeu.slide.cases.Arrivee;
import jeu.slide.cases.Case;
import jeu.slide.cases.Fleche;
import jeu.slide.cases.Glace;
import jeu.slide.cases.Ice;
import jeu.slide.cases.Porte;
import jeu.slide.cases.Rocher;
import jeu.slide.cases.Sol;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Text;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public class Plateau extends Sequence {
	
	protected Case[][] damierCases;
	protected Sprite[][] damierEntite;
	private int entiteMobileX;
	private int entiteMobileY;
	private int positionJoueurX;
	private int positionJoueurY;
	private Joueur joueur;
	private String nom;
	protected int decompteur; //Affichage de la transition initiale
	protected Text texteDebut;
	private FicheTechniquePlateau fichePlateau;

	
	public Plateau(Slide game, int id, String plateau,Joueur joueur) throws IOException{
		super(game);
		FicheTechniquePlateau fiche = new FicheTechniquePlateau();
		nom = plateau;
		
		BufferedReader chargeur = new BufferedReader(new InputStreamReader(
				Plateau.class.getResourceAsStream("/ressources/plateaux/"+plateau+".plt")));
		final String VIDE = "--";
		String ligne;
		int tx = Integer.parseInt(chargeur.readLine());
		int ty = Integer.parseInt(chargeur.readLine());
		int px = Integer.parseInt(chargeur.readLine());
		int py = Integer.parseInt(chargeur.readLine());
		String dir = chargeur.readLine();
		
		damierEntite = new Sprite[tx+2][ty+2];
		damierCases = new Case[tx+2][ty+2];
		LinkedList<String> file = new LinkedList<String>();
		while(!(ligne = chargeur.readLine()).equals("")){
			file.add(ligne);
		}
		
		for(int i=0;i<ty;i++){
			ligne = chargeur.readLine();
			for(int j=0;j<tx;j++){
				switch(ligne.substring(2*j, 2*(j+1))){
				case "gl":damierCases[j+1][i+1]=new Glace(game);break;
				case "tr":damierCases[j+1][i+1]=new Sol(game);break;
				case "fh":damierCases[j+1][i+1]=new Fleche(game,Direction.HAUT);break;
				case "fg":damierCases[j+1][i+1]=new Fleche(game,Direction.GAUCHE);break;
				case "fd":damierCases[j+1][i+1]=new Fleche(game,Direction.DROITE);break;
				case "fb":damierCases[j+1][i+1]=new Fleche(game,Direction.BAS);break;
				case "ar":damierCases[j+1][i+1]=new Arrivee(game,file.poll());break;
				case "ri":damierCases[j+1][i+1]=new Rocher(game);break;
				case "po":damierCases[j+1][i+1]=new Porte(game);break;
				case "gc":damierCases[j+1][i+1]=new Ice(game);break;
				}
			}				
			damierCases[tx+1][i+1] =new  Rocher(game);			
			damierCases[0][i+1] =new  Rocher(game);	
		}
		
		for (int i=0; i<tx; i++) {
			damierCases[i+1][0] =new  Rocher(game);
			damierCases[i+1][ty+1] =new Rocher(game);
		}
		damierCases[0][0] =new  Rocher(game);	
		damierCases[tx+1][0] =new  Rocher(game);	
		damierCases[0][ty+1] =new Rocher(game);	
		damierCases[tx+1][ty+1] =new  Rocher(game);
		
		chargeur.readLine();
		for(int i=0;i<ty;i++){
			ligne = chargeur.readLine();
			for(int j=0;j<tx;j++){
				String code = ligne.substring(2*j, 2*j+2);
				if(!code.equals(VIDE)){
					switch(code){
					case "rm":damierEntite[j+1][i+1]=new Sprite(game,TextureEntite.ROCHERMOBILE);break;
					case "cl":damierEntite[j+1][i+1]=new Sprite(game,TextureEntite.CLE);break;
					default:break;
					}
					damierEntite[j+1][i+1].getRender().setPosition((j+1)*Case.TAILLECASEX,(i+1)*Case.TAILLECASEY);
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
		entiteMobileX = -1;
		entiteMobileY = -1;
		positionJoueurX = px;
		positionJoueurY = py;
		this.joueur = joueur;
		damierEntite[px][py]=joueur;
		this.joueur.getRender().setPosition(px*Case.TAILLECASEX,py*Case.TAILLECASEY);
		decompteur = 128;
		String texte = (fiche.nom!=null)?fiche.nom:"Niveau"+plateau ;
		texteDebut = new Text(texte, ChargeurFont.Stocky.getFont(),80);
		texteDebut.setColor(Color.BLUE);
		texteDebut.setPosition(10,10);
		fichePlateau = fiche;
		System.out.println("coucou");
		setRender(new RenderPlateau());
		System.out.println("ok ? "+getRender());
	}
	
	@Override
	public void processInputs(GameController game){
		Slide slide = (Slide) game;
		Direction sens=null;
		for(Event event : slide.getEvents()){
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
						
					break;
					case R:
						game.ajouterEvenement( NewEventGame.RESTART );
					break;
					case Q:
						game.stop();
					break;
					case NUMPAD5:
					String[] listePlateau;
					try {
						listePlateau = new File(Plateau.class.getResource("/ressources/plateaux").toURI()).list();
						String plateauACharger = (String)JOptionPane.showInputDialog(null,"Niveau a charger :",
								"Mode cheat activated !",JOptionPane.QUESTION_MESSAGE,null,listePlateau,null);
						if (plateauACharger!=null){
							plateauACharger = plateauACharger.substring(0, plateauACharger.length()-4);
							game.charger(new Plateau(slide,0,plateauACharger, joueur));
							game.liberer();
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
		if(sens!=null && entiteMobileX == -1 && entiteMobileY == -1) {
			entiteMobileX = positionJoueurX;
			entiteMobileY = positionJoueurY;
			joueur.setElement(sens);
			joueur.setMouvement(sens.getSensX(),sens.getSensY());
		}
	}
	
	@Override
	public void processEventGame(GameController game){
		Slide slide = (Slide) game;
		for(EventGame eg : game.getEventsGame()){
			switch((NewEventGame)eg){
			case COUCOU:break;
			case RESTART:
				try {
					game.charger(new Plateau(slide,0,nom, joueur));
					game.liberer();
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
	public void update(GameController game){
		Slide slide = (Slide) game;
		if(entiteMobileX != -1 && entiteMobileY != -1 ){
			Sprite mobile=damierEntite[entiteMobileX][entiteMobileY];
			if (mobile.debutMouvement) {
				/* Phase1 */
				int nouvellesCoordoneesX = entiteMobileX + mobile.getMouvementX();
				int nouvellesCoordoneesY = entiteMobileY + mobile.getMouvementY();
				Sprite entiteSuivante =damierEntite[nouvellesCoordoneesX][nouvellesCoordoneesY];
				if ( entiteSuivante!= null && !entiteSuivante.isFantome()){
					entiteSuivante.setMouvement( mobile.getMouvementX(),mobile.getMouvementY());
					mobile.setMouvement(0,0);
					entiteMobileX= nouvellesCoordoneesX;
					entiteMobileY = nouvellesCoordoneesY;
				} else {
					damierCases[nouvellesCoordoneesX][nouvellesCoordoneesY].collision(mobile);
					if (mobile.getMouvementX() == 0 && mobile.getMouvementY() == 0) {
						entiteMobileX =-1;
						entiteMobileY = -1;
					}else{
						damierEntite[entiteMobileX][entiteMobileY] = null;
						damierEntite[nouvellesCoordoneesX][nouvellesCoordoneesY] = mobile;
						entiteMobileX = nouvellesCoordoneesX;
						entiteMobileY = nouvellesCoordoneesY;
						if(mobile==joueur){
							positionJoueurX = nouvellesCoordoneesX;
							positionJoueurY = nouvellesCoordoneesY;
						}
					}
					mobile.update(game);
				}
			}else if ( mobile.mouvementTermine() ) {
				/* Phase 2 */
				Case destination = damierCases[entiteMobileX][entiteMobileY];
				destination.interaction(mobile, game);
				if(destination instanceof Arrivee && mobile == joueur){
					changerNiveau(slide, (Arrivee) destination);
				}
				if (mobile.getMouvementX() == 0 && mobile.getMouvementY() == 0) {
					entiteMobileX=-1;
					entiteMobileY=-1;
				}
			}else{
				mobile.update(game);
			}
		}
	}

	private void changerNiveau(Slide slide, Arrivee arrivee){
		try {
			String nouveauNiveau = arrivee.getNiveauSuivant();
			slide.liberer();
			if(nouveauNiveau != null){
				slide.charger(new Plateau(slide,0,nouveauNiveau, joueur));
			}else{
				slide.charger(new Fin(slide,7));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
