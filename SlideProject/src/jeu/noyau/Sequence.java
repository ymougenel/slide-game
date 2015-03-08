package jeu.noyau;

import jeu.noyau.render.RenderSequence;


public abstract class Sequence {
	
	public enum Mode {
		Active, 
		Pause,
		Background;
	}
	
	protected int id;
	protected Mode mode;
	protected GameController game;
	protected RenderSequence render;
	
	
	protected Sequence(GameController game, int idSeq) {
		this.id = idSeq;
		this.mode = Mode.Pause;
		this.game = game;
	}
	
	public GameController getGame() {
		return game;
	}
	
	public RenderSequence getRender() {
		return render;
	}
	
	public void setMode(Mode mode){
		this.mode=mode;
	}
	
	public Mode getMode() {
		return mode;
	}
	
	protected abstract void init();
	protected abstract void processInputs();
	protected abstract void processEventGame();
	protected abstract void update();
	protected void render(){
		render.render(game, this);
	}
}
