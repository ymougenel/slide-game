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
import slide.cases.Glace;
import slide.cases.Sol;

public class Terrain2 {

	public static void main(String[] args) {
		Joueur joueur = new Joueur();
		Case glace = Glace.getInstance();
		Case sol = Sol.getInstance();
		
		Terrain terrain = new  Terrain(8);
		
		
		/* Traitement des cases */
		/* TODO Corriger linitialisation du damier */
		//terrain.remplirDamier(glace);
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				terrain.cases[i][j] = glace;
			}
		}

		/* Traitementes des entites */
		TextureEntite RI = TextureEntite.ROCHERIMMOBILE;
		TextureEntite RM = TextureEntite.ROCHERMOBILE;
		
		terrain.placerBordure(RI);
		
		terrain.placerEntiteImmobile(3, 5, RI);
		terrain.placerEntiteImmobile(3, 6, RI);
		terrain.placerEntiteImmobile(4, 4, RI);
		terrain.placerEntiteImmobile(4, 3, RI);
		/*
		terrain.entites[1][1] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		terrain.entites[1][2] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		terrain.entites[3][2] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		terrain.entites[4][3] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		terrain.entites[4][4] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		terrain.entites[4][5] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		terrain.entites[4][6] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		terrain.entites[1][5] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		terrain.entites[5][1] = new Entite(TextureEntite.ROCHERIMMOBILE,false);*/


		/* Bordure */

		terrain.placerEntiteMobile(2, 6, RM);
		terrain.placerEntiteMobile(2, 4, RM);
		terrain.placerEntiteMobile(3, 7, RM);
		
		Plateau plateau0 = new Plateau(terrain.cases,terrain.entites,joueur,new Vector2i(1,6));
		try {
			ObjectOutputStream colimateur = new ObjectOutputStream(
					new FileOutputStream("src/ressources/plateaux/terrain2.plt"));
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
