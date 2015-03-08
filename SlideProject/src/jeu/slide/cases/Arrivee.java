package jeu.slide.cases;

import jeu.noyau.render.ViewController;


public class Arrivee extends Case {
	
	private String niveauSuivant;
	
	public Arrivee(ViewController vc,String niveauSuivant) {
		super(vc,TextureCase.ARRIVEE);
		this.niveauSuivant = niveauSuivant;
	}
	
	public void setNiveauSuivant(String niveauSuivant) {
		this.niveauSuivant = niveauSuivant;
	}
	
	public String getNiveauSuivant() {
		return niveauSuivant;
	}
}
