package jeu.noyau.render;


public interface Render<T extends Renderable> {
	
	void init(T renderable);
	void render(ViewController vc, T renderable);

}
