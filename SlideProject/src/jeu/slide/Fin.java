package jeu.slide;

import java.io.IOException;
import java.io.ObjectInputStream;

import jeu.noyau.GameController;
import jeu.noyau.Sequence;
import jeu.noyau.render.ChargeurFont;
import jeu.noyau.render.Render;
import jeu.noyau.render.ViewController;
import jeu.slide.jsfml.ViewControllerJSFML;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class Fin extends Sequence implements Render<Sequence>{

	private String message="ArcadYann(c) vous félicite!\n ca vous fera 20€";
	private transient Text texte;

	public Fin(Slide game, int id){

		super(game);
		
		this.texte= new Text(message, ChargeurFont.Orange.getFont(), 60);
		texte.setPosition( new Vector2f(0, 250));
		
		texte.setColor(Color.RED);
	}

	@Override
	public void render(ViewController vc, Sequence seq) {
		ViewControllerJSFML view = (ViewControllerJSFML) vc;
		if(getMode() == Mode.Active){
			view.getRenderView().draw(texte);
		}
	}
	
	private void readObject(final ObjectInputStream in) throws IOException,  ClassNotFoundException {
		in.defaultReadObject();
		Font font = new Font();
		try {
			font.loadFromStream(Fin.class.getResourceAsStream("/ressources/polices/orangejuice.ttf"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.texte= new Text(message, font, 60);
		texte.setPosition( new Vector2f(0, 250));
		
		texte.setColor(Color.RED);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameController game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRender(Render<Sequence> render) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(Sequence renderable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processInputs(GameController game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processEventGame(GameController game) {
		// TODO Auto-generated method stub
		
	}
}
