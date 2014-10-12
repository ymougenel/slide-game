package terrains;

import org.jsfml.system.Vector2i;

import jeu.Entite;
import jeu.Entite.TextureEntite;
import slide.Case;
import slide.EntiteImmobile;
import slide.EntiteMobile;
import slide.Glace;
import slide.Joueur;
import slide.Plateau;

public class Terrain0 extends Plateau{

	public Terrain0(Joueur joueur, Vector2i positionJoueurInitiale) {
		super(chargerCases(), chargerEntites(), joueur, positionJoueurInitiale);
	}
	
	private static Case[][] chargerCases(){
		Case glace = Glace.getInstance();
		Case[][] cases = new Case[6][6];
		for(int j=0;j<6;j++){
			for(int i=0;i<6;i++){
				cases[i][j] = glace;
			}
		}
		return cases;
	}

	private static Entite[][] chargerEntites(){
		
		Entite[][] entites = new Entite[6][6];
		
		entites[0][0] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[0][1] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[2][1] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[3][2] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[3][3] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[3][4] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[3][5] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[0][4] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		entites[4][0] = new EntiteImmobile(TextureEntite.ROCHERIMMOBILE);
		
		entites[1][3] = new EntiteMobile(TextureEntite.ROCHERMOBILE);
		
		return entites;
	}

}
