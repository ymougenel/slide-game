package terrains;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.jsfml.system.Vector2i;

import slide.Entite;
import slide.Entite.TextureEntite;
import slide.cases.Case;
import slide.cases.Fleche;
import slide.cases.Glace;
import slide.cases.Sol;
import slide.cases.Fleche.Sens;
import slide.Joueur;
import slide.Plateau;

public class Terrain0 {

	public static void main(String[] args) {
		Joueur joueur = new Joueur();

		// creation damierCase
		Case glace = Glace.getInstance();
		Case[][] cases = new Case[8][8];
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				cases[i][j] = glace;
			}
		}
		
		cases[5][2]=Sol.getInstance();
		cases[5][6]=Fleche.getInstance(Sens.HAUT);

		// creation damierentite
		Entite[][] entites = new Entite[8][8];

		entites[1][1] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		entites[1][2] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		entites[3][2] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		entites[4][3] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		entites[4][4] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		entites[4][5] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		entites[4][6] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		entites[1][5] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		entites[5][1] = new Entite(TextureEntite.ROCHERIMMOBILE,false);

		/* Bordure */
		for (int i = 0; i < 8; i++) {
			entites[0][i] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
			entites[7][i] = new Entite(TextureEntite.ROCHERIMMOBILE,false);

			entites[i][0] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
			entites[i][7] = new Entite(TextureEntite.ROCHERIMMOBILE,false);
		}

		/* Joueur */
		entites[2][4] = new Entite(TextureEntite.ROCHERMOBILE);

		Plateau plateau0 = new Plateau(cases,entites,joueur,new Vector2i(1,6));
		try {
			ObjectOutputStream colimateur = new ObjectOutputStream(
					new FileOutputStream("terrain0.plt"));
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