package jeu.noyau.render;


public interface Renderable {

	Render<? extends Renderable> getRender();
}
