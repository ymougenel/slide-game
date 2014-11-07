package jeu;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public abstract class Jeu {
	//ajout de commentaire inutile
	public interface EventGame{};
	
	private RenderWindow fenetre;
	private Collection<Sequence> sequencesChargees;
	private Collection<Event> events;
	private Collection<EventGame> eventsGame;
	private Stack<EventGame> pileEventGame;
	//duree d'une frame en µseconde;
	public static final float TIME_PER_FRAME = Time.getMilliseconds(1000/60).asMicroseconds();
	
	//Quand xInitThreads passe, les Textures sales trépassent.
	private static native void xInitThreads();
	private static void verifierOs(){
		if(System.getProperty("os.name").contains("Linux")){
			Path library = Paths.get(System.getProperty("user.home"), ".jsfml");
			if(!Files.exists(library)){
				try {
					Files.createDirectories(library);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Path xInit = library.resolve("XInitThreads");
			if(!Files.exists(xInit)){
				try ( OutputStream out = new FileOutputStream(xInit.toString()); 
						InputStream in = Jeu.class.getResourceAsStream("/jeu/XInitThreads")){
					byte[] buffer = new byte[10000];
					out.write(buffer, 0, in.read(buffer));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
			System.load(xInit.toString());
			xInitThreads();
		}
	}
	
	public Jeu(String nom){
		verifierOs();
		this.fenetre = new RenderWindow(new VideoMode(800, 600, 32), nom);
		this.fenetre.setFramerateLimit(60);
		this.fenetre.setVerticalSyncEnabled(true);
		this.fenetre.setKeyRepeatEnabled(false);
		this.sequencesChargees = new LinkedList<Sequence>();
		this.events = new LinkedList<Event>();
		this.eventsGame = new LinkedList<EventGame>();
		this.pileEventGame = new Stack<EventGame>();
	}
	
	/** lever un evenement interne du jeu
	 *
	 * @param event, l'evenement a lever 
	 */
	public void ajouterEvenement(EventGame event){
		pileEventGame.push(event);
	}
	
	//traite les evenements
	private void processEvents(){
		Event event;
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
		this.eventsGame.clear();
		while(! pileEventGame.isEmpty()){
			EventGame eventGame = pileEventGame.pop();
			eventsGame.add(eventGame);
			processEventGame(eventGame);
		}
	}
	
	public void setPause(boolean pause){
		for(Sequence seq : sequencesChargees){
			seq.setPause(pause);
		}
	}
	
	/** obtenir les evenements externes du jeu
	 * 
	 * @return les Event levés depuis les derniers calculs
	 */
	public Collection<Event> getEvents(){
		return new LinkedList<Event>(this.events); 
	}
	
	public Collection<EventGame> getEventsGame(){
		return new LinkedList<EventGame>(this.eventsGame); 
	}
	
	/**activer les calculs de la sequence seq
	 * 
	 * @param seq
	 */
	public void charger(Sequence seq){
		sequencesChargees.add(seq);
	}
	
	/**desactiver les calculs de la sequence seq
	 * 
	 * @param seq
	 */
	public void liberer(Sequence seq){
		sequencesChargees.remove(seq);
	}
	
	protected abstract void processEvent(Event event);
	protected abstract void processEventGame(EventGame event);
	
	/**lance la boucle de rafraichissement du jeu
	 * 
	 */
	protected void run(){
		Clock clock=new Clock();
		float timeSinceLastUpdate=0;
		while (fenetre.isOpen()){ 
	        timeSinceLastUpdate += clock.restart().asMicroseconds();
	        while (timeSinceLastUpdate > TIME_PER_FRAME){
	        	timeSinceLastUpdate -= TIME_PER_FRAME;
	        	processEvents();
	        	for (Sequence seq : sequencesChargees) seq.update(this);
	        }
	        fenetre.clear();
	        for (Sequence seq : sequencesChargees) fenetre.draw(seq);
	        fenetre.display();
	    }
	}
}
