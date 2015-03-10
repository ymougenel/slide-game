package jeu.noyau;

import jeu.noyau.render.RenderEntite;
import jeu.noyau.render.Renderable;
import jeu.noyau.render.ViewController;


public abstract class Entite implements Renderable {

	private Element texture;
	protected int trame;
	protected RenderEntite render;
	private boolean animer;
	private int compteurTrame;
	
	public Entite() {
		this.texture = null;
		this.trame = 0;
		animer=false;
		compteurTrame = 0;
	}
	
	public void move(float depX,float depY){
		render.setPosition(render.getPositionX()+depX, render.getPositionY()+depY);
	}
	
	public RenderEntite getRender() {
		return render;
	}
	
	public Element getElement(){
		return texture;
	}
	
	public void setElement (Element texture){
		this.texture = texture;
		render.setTexture(texture, trame);
	}
	
	public void setAnimation(boolean animer) {
		this.animer = animer;
	}
	
	public boolean isAnimer() {
		return animer;
	}
	
	protected void animer (){
		if (trame != 0 || animer) {
			compteurTrame=(compteurTrame+1)%texture.framesPerTrame();
			if(compteurTrame == 0){
				trame = (trame+1)%texture.getNombreTrames();
				render.setTexture(texture, trame);
			} 
		}
	}
	
	public void draw(ViewController vc){
		render.render(vc, this);
	}
}
