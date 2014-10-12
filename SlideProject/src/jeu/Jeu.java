package jeu;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public abstract class Jeu {
	private RenderWindow fenetre;
	private Collection<Sequence> sequenceAUpdate;
	private Collection<Sequence> sequenceARender;
	private Collection<Event> events;
	private Stack<EventGame> eventsGame;
	public static final Time TIME_PER_FRAME = Time.getMilliseconds(1000/60);
	
	public Jeu(String nom){
		this.fenetre = new RenderWindow(new VideoMode(800, 600, 32), nom);
		this.fenetre.setFramerateLimit(60);
		
		this.sequenceAUpdate = new LinkedList<Sequence>();
		this.sequenceARender = new LinkedList<Sequence>();
		this.events = new LinkedList<Event>();
		this.eventsGame = new Stack<EventGame>();
	}
	
	/** lever un evenement interne du jeu
	 *
	 * @param event, l'evenement a lever 
	 */
	public void add(EventGame event){
		eventsGame.push(event);
	}
	
	//traite les evenements
	private void processEvents(){
		Event event=null;
		this.events.clear();
		//depiler les evenements externes
		while ((event=fenetre.pollEvent())!=null){
			events.add(event);
            if (event.type == Event.Type.CLOSED){
                fenetre.close();
            }
            processEvent(event);
        }
		//depiler les evenements internes
		while(! eventsGame.isEmpty()){
			processEventGame(eventsGame.pop());
		}
	}
	
	/** obtenir les evenements externes du jeu
	 * 
	 * @return les Event levés depuis les derniers calculs
	 */
	public Collection<Event> getEvents(){
		return new LinkedList<Event>(this.events); 
	}
	
	/**activer les calculs de la sequence seq
	 * 
	 * @param seq
	 */
	public void chargerUpdate(Sequence seq){
		sequenceAUpdate.add(seq);
	}
	
	/**desactiver les calculs de la sequence seq
	 * 
	 * @param seq
	 */
	public void libererUpdate(Sequence seq){
		sequenceAUpdate.remove(seq);
	}
	
	/**activer le rendu de la sequence seq
	 * 
	 * @param seq
	 */
	public void chargerRender(Sequence seq){
		sequenceARender.add(seq);
	}
	
	/**desactiver le rendu de la sequence seq
	 * 
	 * @param seq
	 */
	public void libererRender(Sequence seq){
		sequenceARender.remove(seq);
	}
	
	protected abstract void processEvent(Event event);
	protected abstract void processEventGame(EventGame event);
	
	/**lance la boucle de rafraichissement du jeu
	 * 
	 */
	protected void run(){
		Clock clock=new Clock();
		Time timeSinceLastUpdate=Time.ZERO;
		while (fenetre.isOpen()){ 
	        timeSinceLastUpdate = Time.add( timeSinceLastUpdate, clock.restart() );
	        while (timeSinceLastUpdate.compareTo(TIME_PER_FRAME) >0){
	        	timeSinceLastUpdate = Time.sub( timeSinceLastUpdate, TIME_PER_FRAME );
	        	processEvents();
	        	for (Sequence seq : sequenceAUpdate) {
					seq.update(this);
				}
	        }
	        fenetre.clear();
	        for (Sequence seq : sequenceARender) {
				fenetre.draw(seq);
			}
	        fenetre.display();
	    }
	}
}
