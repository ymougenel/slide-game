package jeu.slide;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import jeu.noyau.GameController;
import jeu.slide.jsfml.ViewControllerJSFML;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class Slide extends GameController implements ViewControllerJSFML {
	
	private RenderWindow fenetre;
	private List<Event> events;
	
	public Slide() {
		super("Slide");
		this.fenetre = new RenderWindow(new VideoMode(800, 600, 32), "Slide");
		this.fenetre.setFramerateLimit(60);
		this.fenetre.setVerticalSyncEnabled(true);
		this.fenetre.setKeyRepeatEnabled(false);
		this.events = new LinkedList<Event>();
	}
	
	public float getTimePerFrame(){
		return 1000000f/60;
	}
	
	protected void processEvents(){
		Event event;
		this.events.clear();
		//depiler les evenements externes
		while ((event=fenetre.pollEvent())!=null){
			events.add(event);
            if (event.type == Event.Type.CLOSED){
                this.stop();
            }
        }
		super.processEvents();
	}
	
	public Collection<Event> getEvents(){
		return new LinkedList<Event>(events);
	}
	
	protected void start(){
		Clock clock=new Clock();
		float timeSinceLastUpdate=0;
		while (isRunning()){ 
	        timeSinceLastUpdate += clock.restart().asMicroseconds();
	        while (timeSinceLastUpdate > getTimePerFrame()){
	        	timeSinceLastUpdate -= getTimePerFrame();
	        	processEvents();
	        	inputs();
	        	update();
	        }
	        fenetre.clear();
	        render();
	        fenetre.display();
	    }
	}
	
	@Override
	public void stop() {
		fenetre.close();
		super.stop();
	}

	public static void main(String[] args) throws IOException {
		Slide slide = new Slide();
		slide.charger(new Plateau(slide,0,"0-debut", new Joueur(slide)));
		//slide.charger(new MenuPrincipale(slide, 0));
		slide.start();
	}

	@Override
	public RenderWindow getRenderView() {
		return fenetre;
	}
}