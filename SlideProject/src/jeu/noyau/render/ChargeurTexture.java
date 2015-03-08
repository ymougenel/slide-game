package jeu.noyau.render;

import jeu.noyau.Element;


public abstract class ChargeurTexture extends Chargeur {
	
	protected int tailleX;
	protected int tailleY;
	
	public ChargeurTexture(String path,int tailleX,int tailleY) {
		super(path);
		this.tailleX=tailleX;
		this.tailleY=tailleY;
	}
	
	
	public void addTexture(RenderEntite render, Element element,int trame){
		addTexture(render, element.ordinal(),trame);
	}
	
	public void addTexture(RenderEntite render, int element, int trame){
		render.setTextureRect( element*tailleX, trame*tailleY, tailleX, tailleY);
	}
}
