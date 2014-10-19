package slide;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.jsfml.system.Vector2i;

public class Exporteur2000 {

	public static void main(String[] args) {
		Joueur joueur = new Joueur();
		Plateau Terrain0 = new terrains.Terrain0(joueur, new Vector2i(1,6) );
		try {
			ObjectOutputStream colimateur = new ObjectOutputStream( new FileOutputStream("terrain0.plt"));
			colimateur.writeObject(Terrain0);
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
