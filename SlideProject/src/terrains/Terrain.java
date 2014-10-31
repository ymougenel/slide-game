package terrains;

import slide.Entite;
import slide.Entite.TextureEntite;
import slide.cases.Case;

public class Terrain {
	private Case[][] cases;
	private Entite[][] entites;
	
	public Terrain() {
		
	}
	private void placerEntiteImmobile (int i, int j, TextureEntite text){
		entites[i][j] =  new Entite(text,false);
	}
}
