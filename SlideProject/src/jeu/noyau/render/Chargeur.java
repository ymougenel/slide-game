package jeu.noyau.render;

public abstract class Chargeur {
	
	private String path;

	public Chargeur(String path){
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	public abstract void charger();
}
