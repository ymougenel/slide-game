package jeu.slide.cases;

import jeu.noyau.Element;
import jeu.noyau.Entite;
import jeu.noyau.GameController;
import jeu.noyau.render.RenderEntite;
import jeu.noyau.render.ViewController;
import jeu.slide.Sprite;
import jeu.slide.jsfml.ChargeurTextureJSFML;
import jeu.slide.jsfml.RenderEntiteJSFML;

public abstract class Case extends Entite {
	
	public static RenderEntite renderClass=new RenderEntiteJSFML(new ChargeurTextureJSFML("cases.png", Case.TAILLECASEX,Case.TAILLECASEY));
	public static final int TAILLECASEX = 16;
	public static final int TAILLECASEY = 16;
	
	static{
		renderClass.getChargeur().charger();
	}
	
	protected Sprite ecraseur;
	
	public enum TextureCase implements Element{
		GLACE,
		ROCHER,
		TERRE,
		EAU,
		MOUSSE,
		FLECHE,
		ARRIVEE,
		PORTE;

		@Override
		public int getNombreTrames() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int framesPerTrame() {
			// TODO Auto-generated method stub
			return 5;
		}
	}
	public Case (ViewController vc, TextureCase texture){
		super();
		this.render = (RenderEntite) vc.createRender(this);
		setElement(texture);
		render.setTexture(texture, 0);
		render.setOrigin(8,8);
		ecraseur=null;
	}
	
	public void interaction(Sprite sprite, GameController jeu){
		sprite.setMouvement(0, 0);
	}
	
	public void collision (Sprite collisioneur){
		ecraseur = collisioneur;
	}
}
