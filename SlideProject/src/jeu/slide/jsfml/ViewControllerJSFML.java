package jeu.slide.jsfml;

import jeu.noyau.render.ViewController;

import org.jsfml.graphics.RenderWindow;

public interface ViewControllerJSFML extends ViewController {
	
	@Override
	public RenderWindow getRenderView();

}
