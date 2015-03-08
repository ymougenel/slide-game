package jeu.noyau.render;

import jeu.noyau.Element;
import jeu.noyau.Entite;

public interface RenderEntite extends Render<Entite> {
	
	float getPositionX();
	float getPositionY();
	void setPosition(float x, float y);
	void setTextureRect(int x, int y, int tailleX, int tailleY);
	void setOrigin(int x, int y);
	void setScale(float x, float y);
	void setRotation(float angle);
	ChargeurTexture getChargeur();
	void setTexture(Element element,int trame);
	
}
