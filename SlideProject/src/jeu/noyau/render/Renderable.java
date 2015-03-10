package jeu.noyau.render;

import jeu.noyau.GameController;


public interface Renderable {

	void init();
	void update(GameController game);
	Render<?> getRender();
}
