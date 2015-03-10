package jeu.noyau;

import org.jsfml.system.Vector2i;

public enum Direction implements ChargeurTexture.Element {
	
	DROITE(new Vector2i(1,0)),
	HAUT(new Vector2i(0,-1)),
	GAUCHE(new Vector2i(-1,0)),
	BAS(new Vector2i(0,1));
	
	private Vector2i sens;
	
	private Direction(Vector2i sens){
		this.sens = sens;
	}
	
	public Vector2i getSens(){
		return sens;
	}

	@Override
	public int getNombreTrames() {
		return 2;
	}

}
