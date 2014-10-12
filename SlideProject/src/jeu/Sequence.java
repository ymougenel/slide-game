package jeu;

import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public abstract class Sequence implements Drawable {
	
	public abstract void update(Jeu game);
	protected abstract void render(RenderTarget fenetre);
	
	public void draw(RenderTarget fenetre, RenderStates statut) {
		ConstView vue=fenetre.getView();//sauvegarder la vue de la fenetre
		render(fenetre);
		fenetre.setView(vue);//remettre la vue originelle de la fenetre
	}
}
