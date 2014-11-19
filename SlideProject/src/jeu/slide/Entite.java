package jeu.slide;

import jeu.noyau.ChargeurTexture;
import jeu.noyau.ChargeurTexture.Element;
import jeu.noyau.Jeu;
import jeu.slide.cases.Case;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Entite extends Sprite{
	
	private static float vitesse = 0.25f;
	protected  static Vector2i TAILLEENTITE = new Vector2i(16,16);
	protected Vector2i mouvement;
	private Vector2f positionFinale;
	protected boolean mouvementEnCours;
	private Element textureEntite;
	protected int trameCourante;
	protected ChargeurTexture chargeur;
	private boolean fantome;
	
	private static ChargeurTexture chargeurEntite = new ChargeurTexture("entites.png", TAILLEENTITE,new Color(222, 230, 10) );
	
	public enum TextureEntite implements ChargeurTexture.Element {
		ROCHERMOBILE(1),
		CLE(1);
		
		private int nombreTrame;
		private TextureEntite (int nombre){
			this.nombreTrame = nombre;
		}
		@Override
		public int getNombreTrames() {
			return nombreTrame;
		}
	}
	
	public Entite(TextureEntite texture, boolean estFantome) {
		this();
		chargeur.addTexture(this, texture,0);
		this.textureEntite = texture;
		this.fantome = estFantome;

	}
	
	public Entite() {
		super();
		mouvement = Vector2i.ZERO;	
		positionFinale = Vector2f.ZERO;
		mouvementEnCours=false;
		this.textureEntite = null;
		this.trameCourante =0;
		this.chargeur = chargeurEntite;
		setOrigin(8,8);
		this.fantome=false;
	}
	
	public void setMouvement( Vector2i deplacement){
		if(! deplacement.equals(Vector2i.ZERO) ){
			this.mouvementEnCours=true;
			this.mouvement=deplacement;
			this.positionFinale = new Vector2f(deplacement.x * Case.TAILLECASE.x + getPosition().x, 
											deplacement.y * Case.TAILLECASE.y + getPosition().y);
		}
		else {
			mouvementEnCours = false;
			mouvement = Vector2i.ZERO;
			this.positionFinale=getPosition();
		}
	}
		
			
	public Vector2i getMouvement() {
		return mouvement;
	}
	
	public void update() {
		if( mouvementEnCours) {	
			float distanceX = Jeu.TIME_PER_FRAME * vitesse * mouvement.x / 1000;
			float distanceY = Jeu.TIME_PER_FRAME * vitesse * mouvement.y / 1000;
			this.move(distanceX, distanceY);
			/* TODO ANIMATION FLOWER */
			
			if ( getPosition().x * mouvement.x > positionFinale.x * mouvement.x || getPosition().y *mouvement.y > positionFinale.y * mouvement.y){
				this.setPosition(positionFinale);
				this.mouvementEnCours = false;
			}
		}
		
	}
	
	public boolean mouvementTermine (){
		return positionFinale.equals(getPosition());
	}
	
	public void animer (){
		if (trameCourante != 0 || mouvementEnCours) {
			trameCourante = (trameCourante == textureEntite.getNombreTrames() - 1) ? 0 : trameCourante+1;
		}
		this.chargeur.addTexture(this, textureEntite, trameCourante);
	}
	
	public void setElement (Element element){
		this.textureEntite = element;
		this.chargeur.addTexture(this, element, trameCourante);
	}
	
	public Element getElement(){
		return textureEntite;
	}
	
	public void collision(Entite collisioneur){
		if(!fantome){
			this.setMouvement( collisioneur.getMouvement());
			collisioneur.setMouvement( Vector2i.ZERO);
		}
	}
	
	public boolean isFantome() {
		return fantome;
	}
	
	public void setFantome(boolean fantome) {
		this.fantome = fantome;
	}
	
	@Override
	public void draw(RenderTarget target, RenderStates states) {
		if(!fantome){
			super.draw(target, states);
		}
	}
	
}
