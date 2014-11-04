package terrains;

import org.jsfml.system.Vector2i;

import slide.Entite.TextureEntite;
import slide.cases.Arrivee;
import slide.cases.Case;
import slide.cases.Glace;

public class Terrain2 {

	public static void main(String[] args) {
		Case glace = Glace.getInstance();
		
		Terrain terrain = new  Terrain(10);
		
		
		/* Traitement des cases */
		terrain.remplirDamier(glace);
		terrain.placerCase(3, 3, new Arrivee("sequencefinale.plt"));
		/* Traitementes des entites */
		TextureEntite RI = TextureEntite.ROCHERIMMOBILE;
		TextureEntite RM = TextureEntite.ROCHERMOBILE;
		
		terrain.placerBordure(RI);
		
		terrain.placerEntiteImmobile(2, 3, RI);
		terrain.placerEntiteImmobile(2, 6, RI);
		terrain.placerEntiteImmobile(2, 9, RI);
		terrain.placerEntiteImmobile(3, 2, RI);
		terrain.placerEntiteImmobile(3, 4, RI);
		terrain.placerEntiteImmobile(3, 7, RI);
		terrain.placerEntiteImmobile(5, 2, RI);
		terrain.placerEntiteImmobile(6, 5, RI);
		terrain.placerEntiteImmobile(6, 8, RI);
		terrain.placerEntiteImmobile(7, 2, RI);
		terrain.placerEntiteImmobile(7, 3, RI);
		terrain.placerEntiteImmobile(7, 7, RI);		
		terrain.placerEntiteImmobile(7, 8, RI);
		terrain.placerEntiteImmobile(8, 4, RI);
		terrain.placerEntiteImmobile(8, 7, RI);
		terrain.placerEntiteImmobile(9, 2, RI);		
		terrain.placerEntiteImmobile(9, 3, RI);
		
		terrain.placerEntiteMobile(4, 6, RM);
		terrain.placerEntiteMobile(5, 5, RM);
		terrain.placerEntiteMobile(6, 6, RM);
		terrain.placerEntiteMobile(5, 7, RM);
		terrain.placerEntiteMobile(8, 6, RM);
		
		/* Creation du Plateau */
		terrain.sauvegarderTerrain(2,new Vector2i(4,5));
	}

}
