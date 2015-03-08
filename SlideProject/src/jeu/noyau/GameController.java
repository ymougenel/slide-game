package jeu.noyau;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.jsfml.graphics.Color;

import jeu.noyau.render.Render;
import jeu.noyau.render.RenderFactory;
import jeu.noyau.render.Renderable;
import jeu.noyau.render.ViewController;
import jeu.slide.Joueur;
import jeu.slide.Sprite;
import jeu.slide.cases.Arrivee;
import jeu.slide.cases.Case;
import jeu.slide.cases.Fleche;
import jeu.slide.cases.Glace;
import jeu.slide.cases.Rocher;
import jeu.slide.cases.Sol;
import jeu.slide.jsfml.ChargeurTextureJSFML;
import jeu.slide.jsfml.RenderEntiteJSFMLFactory;

public abstract class GameController implements ViewController {
	//ajout de commentaire inutile
	public interface EventGame{};
	
	private List<Sequence> sequences;
	private Collection<Sequence> sequencesACharger;
	private Collection<EventGame> eventsGame;
	private Stack<EventGame> pileEventGame;
	private boolean running;
	private Map<Class<? extends Renderable>,RenderFactory> renders;
	
	public GameController(String nom){
		//verifierOs();
		this.sequences = new LinkedList<Sequence>();
		this.sequencesACharger = new LinkedList<Sequence>();
		this.eventsGame = new LinkedList<EventGame>();
		this.pileEventGame = new Stack<EventGame>();
		this.running = true;
		this.renders = new HashMap<>();
		ChargeurTextureJSFML chargeur = new ChargeurTextureJSFML("entites.png", Sprite.TAILLEENTITEX,Sprite.TAILLEENTITEY,new Color(222, 230, 10));
		chargeur.charger();
		RenderEntiteJSFMLFactory factory = new RenderEntiteJSFMLFactory(chargeur); 
		renders.put(Entite.class, factory);
		renders.put(Sprite.class, factory);
		chargeur = new ChargeurTextureJSFML("cases.png", Case.TAILLECASEX,Case.TAILLECASEY,new Color(222, 230, 10));
		chargeur.charger();
		factory = new RenderEntiteJSFMLFactory(chargeur);
		renders.put(Case.class, factory);
		renders.put(Glace.class, factory);
		renders.put(Sol.class, factory);
		renders.put(Arrivee.class, factory);
		renders.put(Rocher.class, factory);
		renders.put(Fleche.class, factory);
		
		chargeur = new ChargeurTextureJSFML("j.png", 11,18,new Color(222, 230, 10) );
		chargeur.charger();
		factory = new RenderEntiteJSFMLFactory(chargeur);
		renders.put(Joueur.class, factory);
	}
	
	/** lever un evenement interne du jeu
	 *
	 * @param event, l'evenement a lever 
	 */
	public void ajouterEvenement(EventGame event){
		pileEventGame.push(event);
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public Collection<EventGame> getEventsGame(){
		return new LinkedList<EventGame>(this.eventsGame); 
	}
	
	public <T extends Renderable> Render<? extends Renderable>  createRender(T renderable){
		return renders.get(renderable.getClass()).createRender();
	}
	
	protected void processEvents(){
		sequences.addAll(sequencesACharger);
    	sequencesACharger.clear();
    	
    	//depiler les evenements internes
    	this.eventsGame.clear();
    	while(! pileEventGame.isEmpty()){
    		EventGame eventGame = pileEventGame.pop();
    		eventsGame.add(eventGame);
    	}
	}
	
	/**activer les calculs de la sequence seq
	 * 
	 * @param seq
	 */
	public void charger(Sequence seq){
		sequencesACharger.add(seq);
	}
	
	/**desactiver les calculs de la sequence seq
	 * 
	 * @param seq
	 */
	public void liberer(){
		((LinkedList<Sequence>)sequences).pollLast();
	}
	
	/**lance la boucle de rafraichissement du jeu
	 * 
	 */
	
	protected abstract void start();
	
	public void inputs(){
		for (Sequence seq : sequences) seq.processInputs();
	}
	
	public void update(){
		for (Sequence seq : sequences) seq.update();
	}
	
	public void render(){
		for (Sequence seq : sequences){
			seq.render();
		}
	}
	
	public void stop() {
		// On propose la sauvegarde?
		running=false;
	}
}
