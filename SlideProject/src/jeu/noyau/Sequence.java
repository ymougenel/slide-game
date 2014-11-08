package jeu.noyau;

import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public abstract class Sequence implements Drawable {
	
	private boolean enPause;
	private boolean visible;
	
	
	protected Sequence(){
		this.enPause = false;
		this.visible = true; 
	}
	
	public abstract void activeUpdate(Jeu game);
	public abstract void backgroundUpdate(Jeu game);
	
	public void update(Jeu game) {
		if (!enPause) {
			activeUpdate(game);			
		}
		backgroundUpdate(game);
	}
	protected abstract void render(RenderTarget fenetre);
	
	public void draw(RenderTarget fenetre, RenderStates statut) {		
		if (visible) {
			ConstView vue=fenetre.getView();//sauvegarder la vue de la fenetre
			render(fenetre);
			fenetre.setView(vue);//remettre la vue originelle de la fenetre
		}
	}
	
	public void setVisible (boolean visibilite){
		this.visible = visibilite;
	}
	
	public void setPause ( boolean pause){
		this.enPause = pause;
	}
	
	public boolean isPause() {
		return enPause;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	
}
