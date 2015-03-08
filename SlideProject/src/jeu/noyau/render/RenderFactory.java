package jeu.noyau.render;


public interface RenderFactory {
	Render<? extends Renderable> createRender();
}
