package terrains;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.jsfml.system.Vector2i;

import jeu.Entite;
import jeu.Entite.TextureEntite;
import slide.Case;
import slide.EntiteImmobile;
import slide.EntiteMobile;
import slide.Glace;
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

		// creation damierentite
		Entite[][] entites = new Entite[8][8];

		entites[1][1] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[1][2] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[3][2] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[4][3] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[4][4] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[4][5] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[4][6] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[1][5] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[5][1] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);

		/* Bordure */
		for (int i = 0; i < 8; i++) {
			entites[0][i] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
			entites[7][i] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);

			entites[i][0] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
			entites[i][7] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		}

		/* Joueur */
		entites[2][4] = new EntiteMobile(TextureEntite.ROCHERMOBILE);

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
