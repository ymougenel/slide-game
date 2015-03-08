package jeu.slide;

import jeu.noyau.Element;
import jeu.noyau.Entite;
import jeu.noyau.GameController;
import jeu.noyau.render.RenderEntite;
import jeu.slide.cases.Case;
import jeu.slide.jsfml.ChargeurTextureJSFML;
import jeu.slide.jsfml.RenderEntiteJSFML;

import org.jsfml.graphics.Color;

public class Sprite extends Entite {
	
	public static RenderEntite renderClass=new RenderEntiteJSFML(new ChargeurTextureJSFML("entites.png", Sprite.TAILLEENTITEX,Sprite.TAILLEENTITEY,new Color(222, 230, 10)));
	
	static{
		renderClass.getChargeur().charger();
		System.out.println("texture chargée");
	}
	
	private static float vitesse = 0.2f;
	public static int TAILLEENTITEX = 16;
	public static int TAILLEENTITEY = 16;
	protected int mouvementX;
	protected int mouvementY;
	private float positionFinaleX;
	private float positionFinaleY;
	protected boolean debutMouvement;
	final int timeTrame=15;
	protected boolean fantome;
	
	public enum TextureEntite implements Element {
		ROCHERMOBILE(4,5),
		CLE(1,2);
		
		private int nombreTrame;
		private int frames;
		private TextureEntite (int nombre,int frames){
			this.nombreTrame = nombre;
			this.frames = frames;
		}
		@Override
		public int getNombreTrames() {
			return nombreTrame;
		}
		@Override
		public int framesPerTrame() {
			return frames;
		}
	}
	
	public Sprite(GameController game, TextureEntite texture) {
		this(game);
		setElement(texture);
	}
	
	public Sprite(GameController game) {
		super();
		mouvementX = 0;
		mouvementY = 0;
		positionFinaleX = 0;
		positionFinaleY = 0;
		debutMouvement=false;
		this.fantome=false;
		this.render = (RenderEntite) game.createRender(this);
		render.setOrigin(8,8);
	}
	
	public void setMouvement(int deplacementX,int deplacementY){
		if( deplacementX != 0 || deplacementY != 0 ){
			setAnimation(true);
			this.debutMouvement=true;
			this.mouvementX = deplacementX;
			this.mouvementY = deplacementY;
			this.positionFinaleX = deplacementX * Case.TAILLECASEX + render.getPositionX(); 
			this.positionFinaleY = deplacementY * Case.TAILLECASEY + render.getPositionY();
		}
		else {
			setAnimation(false);
			debutMouvement=false;
			mouvementX = 0;
			mouvementY = 0;
			this.positionFinaleX=render.getPositionX();
			this.positionFinaleY=render.getPositionY();
			
		}
	}
		
			
	public int getMouvementX() {
		return mouvementX;
	}
	
	public int getMouvementY() {
		return mouvementY;
	}
	
	
	public void update(Slide game) {
		if(isAnimer()) {	
			float distanceX = game.getTimePerFrame() * vitesse * mouvementX / 1000;
			float distanceY = game.getTimePerFrame() * vitesse * mouvementY / 1000;
			this.move(distanceX, distanceY);
			debutMouvement=false;
			/* TODO ANIMATION FLOWER */
			
			if ( render.getPositionX() * mouvementX > positionFinaleX * mouvementX || render.getPositionY() *mouvementY > positionFinaleY * mouvementY){
				render.setPosition(positionFinaleX,positionFinaleY);
				setAnimation(false);
			}
		}
		animer();
	}
	
	public boolean mouvementTermine (){
		return (positionFinaleX == render.getPositionX() && positionFinaleY == render.getPositionY() );
	}
	
	public boolean debutMouvement(){
		return debutMouvement;
	}
	
	public boolean isFantome() {
		return fantome;
	}
	
	public void setFantome(boolean fantome) {
		this.fantome = fantome;
	}
}
