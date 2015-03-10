package jeu.noyau;

import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public abstract class Sequence implements Drawable {
	
	void active(Jeu game){
		processActiveEvent(game);
		processActiveEventGame(game);
		activeUpdate(game);
	}
	
	void passive(Jeu game){
		processBackgroundEvent(game);
		processBackgroundEventGame(game);
		backgroundUpdate(game);
	}
	
	public void draw(RenderTarget fenetre, RenderStates statut) {		
			ConstView vue=fenetre.getView();//sauvegarder la vue de la fenetre
			render(fenetre);
			fenetre.setView(vue);//remettre la vue originelle de la fenetre
	}
	
	protected abstract void processActiveEvent(Jeu game);
	protected abstract void processActiveEventGame(Jeu game);
	protected abstract void processBackgroundEvent(Jeu game);
	protected abstract void processBackgroundEventGame(Jeu game);
	protected abstract void activeUpdate(Jeu game);
	protected abstract void backgroundUpdate(Jeu game);
	protected abstract void render(RenderTarget fenetre);
}
