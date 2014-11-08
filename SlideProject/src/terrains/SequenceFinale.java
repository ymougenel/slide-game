package terrains;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import jeu.slide.Fin;

public class SequenceFinale {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Fin fin = new Fin();
		
		try {
			ObjectOutputStream colimateur = new ObjectOutputStream(
					new FileOutputStream("src/ressources/plateaux/sequencefinale.plt"));
			colimateur.writeObject(fin);
			colimateur.flush();
			colimateur.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
