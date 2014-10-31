package slide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import jeu.ChargeurTexture;
import jeu.Jeu;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import slide.cases.Case;

public class Entite extends Sprite implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4940088905580735189L;
	
	private static float vitesse = 0.4f;
	private  static Vector2i TAILLEENTITE = new Vector2i(16,16);
	protected Vector2i mouvement;
	private Vector2f positionFinale;
	private boolean mouvementEnCours;
	private TextureEntite textureEntite;
	private boolean mobile;
	
	private static ChargeurTexture chargeur = new ChargeurTexture(Entite.class.getResourceAsStream("../sprites/entites.png"), TAILLEENTITE,new Color(222, 230, 10) );
	public enum TextureEntite implements ChargeurTexture.Element {
		ROCHERMOBILE,
		ROCHERIMMOBILE,
		EAU;
	}
	
	public Entite(TextureEntite texture) {
		this();
		this.setTexture(chargeur.getTexture(texture));
		this.textureEntite = texture;
		this.mobile=true;
	}
	
	public Entite(TextureEntite texture,boolean mobile){
		this(texture);
		this.mobile=mobile;
	}
	
	public Entite() {
		super();
		mouvement = Vector2i.ZERO;	
		positionFinale = Vector2f.ZERO;
		mouvementEnCours=false;
		this.textureEntite = null;
		this.mobile = true;
	}
	
	public boolean setMouvement( Vector2i deplacement){
		if(mobile){
			this.mouvementEnCours=true;
			this.mouvement=deplacement;
			this.positionFinale = new Vector2f(deplacement.x * Case.TAILLECASE.x + getPosition().x, 
											deplacement.y * Case.TAILLECASE.y + getPosition().y);
		}
		return mobile;
	}
		
			
	public Vector2i getMouvement() {
		return mouvement;
	}
	
	public boolean transmettreMouvement( Entite receptrice){
		boolean retour=receptrice.setMouvement(mouvement);
		this.mouvementEnCours = false;
		this.mouvement= Vector2i.ZERO;
		
		return retour;
	}
	
	public void update() {
		if( mouvementEnCours) {	
			System.out.println("position " +getPosition()+" vers "+positionFinale);
			float distanceX = Jeu.TIME_PER_FRAME * vitesse * mouvement.x / 1000;
			float distanceY = Jeu.TIME_PER_FRAME * vitesse * mouvement.y / 1000;
			this.move(distanceX, distanceY);
			/* TODO ANIMATION FLOWER */
			
			if ( getPosition().x * mouvement.x > positionFinale.x * mouvement.x || getPosition().y *mouvement.y > positionFinale.y * mouvement.y){
				this.setPosition(positionFinale);
				System.out.println("Wazaaaz");
				this.mouvementEnCours = false;
				System.out.println("mouvement fini");
			}
		}
		
	}
	
	public boolean mouvementTermine (){
		return positionFinale.equals(getPosition());
	}
	
	private void readObject(final ObjectInputStream in) throws IOException,  ClassNotFoundException {
		in.defaultReadObject();
		if (textureEntite != null) {
			this.setTexture( chargeur.getTexture(textureEntite));
		}
		setPosition( (Vector2f)in.readObject() );
		setOrigin( (Vector2f)in.readObject() );
		
			//sprite.setOrigin(8, 8);
	}
	
	private void writeObject(final ObjectOutputStream out) throws IOException{
		out.defaultWriteObject();
		out.writeObject(getPosition());
		out.writeObject(getOrigin());
	}
	
}
