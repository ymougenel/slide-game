package jeu.noyau.render;



public interface ViewController {

	public <T extends Renderable> Render<? extends Renderable> createRender(T renderable);
	
	Object getRenderView();
}
