package jeu.noyau;

import jeu.noyau.render.Render;
import jeu.noyau.render.Renderable;


public abstract class Sequence implements Renderable {
	
	protected int id;
	private Mode mode;
	private Render<Sequence> render;
	
	public enum Mode {
		Active, 
		Pause,
		Background;
	}
	
	protected Sequence(GameController game){
		id = 0;
		mode = Mode.Pause;
		//game.getViewController().addRender(this);
	}
	
	public int getId() {
		return id;
	}
	
	public Render<Sequence> getRender(){
		return render;
	}
	
	public void setRender(Render<Sequence> render) {
		this.render = render;
	}
	
	public void setMode(Mode mode){
		this.mode = mode;
	}
	public Mode getMode(){
		return mode;
	}
	
	public abstract void processInputs(GameController game);
	public abstract void processEventGame(GameController game);
}
