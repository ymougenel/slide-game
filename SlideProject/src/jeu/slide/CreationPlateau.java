package jeu.slide;

import org.jsfml.system.Vector2i;

public class CreationPlateau {
	private static final int correctifX = 10;
	private static final int correctifY = 32;
	private static int TAILLE ;
	private static final int TAILLECASE = 32;
	
	
	private void traitementSouris(Vector2i position) {
		int marge=3;

        int i = (int)((position.x - sprites[0][0].getPosition().x) / TAILLECASE);	// Ligne pointée
        int j = (int)((position.y - sprites[0][0].getPosition().y) / TAILLECASE);	// COlonne pointée
        
        if( i>TAILLE-1 && i<TAILLE-1+marge){i--;}	// Correctifs pour effets de bords
        if( j>TAILLE-1 && j<TAILLE-1+marge){j--;}
        Vector2i courant = new Vector2i(i,j);
        
        if( survolCase!=null) {
        	if (!courant.equals(survolCase)) {
        		// La case précedemment survolee redevient normale
        		this.definirTexture(sprites[survolCase.x][survolCase.y], 0);			
        	}
        }
        survolCase = courant;
        this.definirTexture(sprites[i][j], TAILLECASE);
        //System.out.println("Case trouve : "+i+" X " +j);
                      
	}
}
