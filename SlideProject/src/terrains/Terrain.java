package terrains;

import slide.Entite;
import slide.Entite.TextureEntite;
import slide.cases.Case;
import slide.cases.Glace;
import slide.cases.Case.TextureCase;

public class Terrain {
	public Case[][] cases;
	public Entite[][] entites;
	public int TAILLE;
	public Case glace = Glace.getInstance();
	
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
	
}
