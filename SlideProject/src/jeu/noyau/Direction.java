package jeu.noyau;

public enum Direction implements Element {
	
	DROITE(1,0),
	HAUT(0,-1),
	GAUCHE(-1,0),
	BAS(0,1);
	
	private int sensX;
	private int sensY;
	
	private Direction(int sensX,int sensY){
		this.sensX = sensX;
		this.sensY = sensY;
	}
	
	public int getSensX(){
		return sensX;
	}
	
	public int getSensY(){
		return sensY;
	}

	@Override
	public int getNombreTrames() {
		return 2;
	}

	@Override
	public int framesPerTrame() {
		return 5;
	}

}
