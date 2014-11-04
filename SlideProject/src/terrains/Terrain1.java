package terrains;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

import slide.Entite;
import slide.Entite.TextureEntite;
import slide.Joueur;
import slide.Plateau;
import slide.cases.Arrivee;
import slide.cases.Case;
import slide.cases.Fleche;
import slide.cases.Glace;
import slide.cases.Sol;
import slide.cases.Fleche.Sens;

public class Terrain1 {

	public static void main(String[] args) {
		Joueur joueur = new Joueur();
		Case glace = Glace.getInstance();
		Case sol = Sol.getInstance();
		
		Terrain terrain = new  Terrain(8);
		
		
		/* Traitement des cases */
		terrain.remplirDamier(glace);
		terrain.cases[6][6]= new Arrivee("terrain2.plt");
		terrain.cases[3][3]=Fleche.getInstance(Sens.DROITE);
		terrain.cases[6][3]=Fleche.getInstance(Sens.BAS);
		/* Traitementes des entites */
		TextureEntite RI = TextureEntite.ROCHERIMMOBILE;
		TextureEntite RM = TextureEntite.ROCHERMOBILE;
		
		terrain.placerBordure(RI);
		
		terrain.placerEntiteImmobile(3, 5, RI);
		terrain.placerEntiteImmobile(3, 6, RI);
		
		terrain.placerEntiteImmobile(3, 2, RI);
		terrain.placerEntiteImmobile(4, 2, RI);
		terrain.placerEntiteImmobile(5, 2, RI);
		terrain.placerEntiteImmobile(6, 2, RI);
		terrain.placerEntiteImmobile(7, 2, RI);
		
		terrain.placerEntiteImmobile(3, 3, RI);
		terrain.placerEntiteImmobile(4, 3, RI);
		terrain.placerEntiteImmobile(5, 3, RI);
		terrain.placerEntiteImmobile(6, 3, RI);
		terrain.placerEntiteImmobile(7, 3, RI);	

		terrain.placerEntiteMobile(2, 4, RM);
		terrain.placerEntiteMobile(2, 6, RM);
		terrain.placerEntiteMobile(3, 7, RM);
		terrain.placerEntiteMobile(5, 7, RM);
		

		/* Creation du Plateau */
		Plateau plateau0 = new Plateau(terrain.cases,terrain.entites,joueur,new Vector2i(1,6));
		try {
			ObjectOutputStream colimateur = new ObjectOutputStream(
					new FileOutputStream("src/ressources/plateaux/terrain1.plt"));
			colimateur.writeObject(plateau0);
			colimateur.flush();
			colimateur.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
