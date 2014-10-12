package slide;

import java.io.IOException;

import org.jsfml.graphics.Image;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.system.Vector2i;

public abstract class Case {
	protected static Image image = chargerImage();
	
	private static Image chargerImage(){
			Image image = new Image();
			try {
				image.loadFromStream(Case.class.getResourceAsStream("../sprites/cases.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return image;
	}		
	protected Sprite sprite;
	public static final Vector2i TAILLECASE = new Vector2i(16, 16);
	
	public enum TextureCase {
		GLACE,
		TERRE,
		EAU;
	}
	public Case (TextureCase texture){
		
		sprite= new Sprite();
		try {
			int ordinal = texture.ordinal();
			Texture fond = new Texture();
			fond.loadFromImage(image, new IntRect(ordinal*TAILLECASE.x, 0, (ordinal+1)*TAILLECASE.x, TAILLECASE.y) );
			sprite.setTexture(fond);
			//sprite.setOrigin(8, 8);
		}
		catch (TextureCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  Sprite getSprite() {
		return this.sprite;
	}
	public abstract Vector2i interaction(Vector2i vitesse);
	
	public static Vector2i getTailleCase() {
		return TAILLECASE;
	}
}