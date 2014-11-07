package terrains;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import org.jsfml.system.Vector2i;

import slide.Entite;
import slide.Entite.TextureEntite;
import slide.Joueur;
import slide.Plateau;
import slide.cases.Arrivee;
import slide.cases.Case;
import slide.cases.Fleche;
import slide.cases.Fleche.Sens;
import slide.cases.Glace;
import slide.cases.Sol;

public class Terrain {
	private Case[][] cases;
	private Entite[][] entites;
	private int TAILLE;
	
	
	public Terrain(int taille) {
		this.cases = new Case[taille][taille];
		this.entites = new Entite[taille][taille];
		this.TAILLE = taille;
		
	}
	public void placerEntiteImmobile(int i, int j, TextureEntite texture){
		entites[i-1][j-1] =  new Entite(texture,false);
	}
	
	public void placerEntiteMobile(int i, int j, TextureEntite texture){
		entites[i-1][j-1] =  new Entite(texture,true);
	}
	
	public void placerBordure( TextureEntite texture) {
		for (int i = 0; i < TAILLE; i++) {
			entites[i][0] = new Entite(texture,false);
			entites[i][TAILLE-1] = new Entite(texture,false);
		/*}
		for (int i = 1; i < TAILLE-1; i++) {*/
			entites[0][i] = new Entite(texture,false);
			entites[TAILLE-1][i] = new Entite(texture,false);
		}
	}
	
	public void remplirDamier (Case type){
		for (int i=0; i< TAILLE; i++){
			for (int j=0; j< TAILLE; j++){
				cases[i][j] = type; 
			}
		}
	}
	
	public void placerCase (int i,int j, Case type) {
		cases[i-1][j-1] = type;
	}
	
	public void sauvegarderTerrain(int numero,Vector2i positionDepart){
		//Plateau plateau0 = new Plateau(cases,entites,new Joueur(),positionDepart);
		try (ObjectOutputStream colimateur = new ObjectOutputStream(
				new FileOutputStream("src/ressources/plateaux/terrain"+numero+".plt"))){
			//colimateur.writeObject(plateau0);
			colimateur.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
