package jeu.slide;

import jeu.noyau.Sequence;

public abstract class SlideSequence extends Sequence{

	public SlideSequence(Slide game, int id) {
		super(game,id);
	}
	
	@Override
	public Slide getGame() {
		// TODO Auto-generated method stub
		return (Slide)super.getGame();
	}
}
