package terrains;

import org.jsfml.system.Vector2i;

import slide.Entite.TextureEntite;
import slide.cases.Arrivee;
import slide.cases.Case;
import slide.cases.Fleche;
import slide.cases.Fleche.Sens;
import slide.cases.Glace;

public class Terrain1 {

	public static void main(String[] args) {
		Case glace = Glace.getInstance();
		
		Terrain terrain = new  Terrain(8);
		
		
		/* Traitement des cases */
		terrain.remplirDamier(glace);
		terrain.placerCase(7, 7,  new Arrivee("terrain2.plt"));
		terrain.placerCase(4, 4, Fleche.getInstance(Sens.DROITE));
		terrain.placerCase(7,4,Fleche.getInstance(Sens.BAS));
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
		terrain.sauvegarderTerrain(1, new Vector2i(1,6));
	}

}
