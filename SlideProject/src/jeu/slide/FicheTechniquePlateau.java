package jeu.slide;

public class FicheTechniquePlateau {

	public String auteur;
	public String date;
	public String nom;
	public String informations;
	public String indice;
	public int numero;
	
	public void afficher(){
		System.out.println(nom);
		System.out.println("Details du plateau :");
		System.out.println("\t Nom : "+nom);
		System.out.println("\t Auteur"+auteur);
		System.out.println("\t date"+date);
		
		if (informations != null) {
			System.out.println("Informations" + informations);
		}
		if ( indice != null) {
			System.out.println("Indice" + indice);			
		}

	}

}
