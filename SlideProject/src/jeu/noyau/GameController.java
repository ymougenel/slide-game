package jeu.noyau;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import jeu.noyau.render.ViewController;

public abstract class GameController {
	//ajout de commentaire inutile
	public interface EventGame{};
	
	private List<Sequence> sequences;
	private Collection<Sequence> sequencesACharger;
	private Collection<EventGame> eventsGame;
	private Stack<EventGame> pileEventGame;
	private boolean running;
	
	public GameController(String nom){
		//verifierOs();
		this.sequences = new LinkedList<Sequence>();
		this.sequencesACharger = new LinkedList<Sequence>();
		this.eventsGame = new LinkedList<EventGame>();
		this.pileEventGame = new Stack<EventGame>();
		this.running = true;
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
		for (Sequence seq : sequences) seq.processInputs(this);
	}
	
	public void update(){
		for (Sequence seq : sequences) seq.update(this);
	}
	
	public void render(){
		for (Sequence seq : sequences){
			seq.getRender().render(getViewController(), seq);
		}
	}
	
	public abstract ViewController getViewController();
	public abstract float getTimePerFrame();
	
	public void stop() {
		// On propose la sauvegarde?
		running=false;
	}
}
