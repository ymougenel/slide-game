package jeu;

import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public abstract class Sequence implements Drawable {
	
	private boolean estEnPause;
	private boolean estVisible;
	
	public void Sequence (){
		this.estEnPause = true;
		this.estVisible = false; 
	}
	
	public abstract void activeUpdate(Jeu game);
	public abstract void backgroundUpdate(Jeu game);
	public void update(Jeu game) {
		if (!estEnPause) {
			activeUpdate(game);			
		}
		backgroundUpdate(game);
	}
	protected abstract void render(RenderTarget fenetre);
	
	public void draw(RenderTarget fenetre, RenderStates statut) {
		ConstView vue=fenetre.getView();//sauvegarder la vue de la fenetre
		if (estVisible) {
			render(fenetre);
		}
		fenetre.setView(vue);//remettre la vue originelle de la fenetre
	}
	
	public void setVisible (boolean visibilite){
		this.estVisible = visibilite;
	}
	
	public void setPause ( boolean pause){
		this.estEnPause = pause;
	}
}
