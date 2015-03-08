package jeu.noyau.render;


public interface Render<T extends Renderable> {
	
	void init(T renderable);
	void draw(ViewController vc, T renderable);

}
