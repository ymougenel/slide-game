package jeu.noyau;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Shape;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Boutton extends Sprite {

	private Text texte;
	private Shape rectangle;
	
	public Boutton( Texture texture, String message, Font font) {
		super(texture);
		this.texte = new Text(message, font);
		texte.setCharacterSize(35);
		rectangle = new RectangleShape(new Vector2f(texte.getGlobalBounds().width,texte.getGlobalBounds().height));
		rectangle.setPosition(texte.getPosition());
		rectangle.setFillColor(Color.TRANSPARENT);
		rectangle.setOutlineColor(Color.BLUE);
		rectangle.setOutlineThickness(1);
	}
	
	public void setPosition ( Vector2f vecteur) {
		super.setPosition(vecteur);
		Vector2f positionTexte = new Vector2f(	vecteur.x + super.getGlobalBounds().width/2 - texte.getGlobalBounds().width /2, 
												vecteur.y + super.getGlobalBounds().height/2-texte.getGlobalBounds().height*5/6) ;
			
		this.texte.setPosition( positionTexte);
		rectangle.setPosition(texte.getPosition());
		
		/*
		System.out.println("---------------------");
		System.out.println(texte.getString());
		System.out.println("Position du sprite :"+ vecteur);
		System.out.println("taille du sprite: largeur->"+ super.getGlobalBounds().width + "  hauteur->" + super.getGlobalBounds().height );
		System.out.println("Position du texte :"+ positionTexte);
		System.out.println("Taille du texte : "+ this.texte.getGlobalBounds());
		System.out.println("");
		*/
	}
	
	public void setOrigine ( Vector2f vecteur) {
		super.setOrigin(vecteur);
		this.texte.setOrigin(vecteur);
	}

	@Override
	public void draw(RenderTarget fenetre, RenderStates status) {
		super.draw(fenetre, status);
		fenetre.draw(texte, status);
		//fenetre.draw(rectangle);
	}

}
