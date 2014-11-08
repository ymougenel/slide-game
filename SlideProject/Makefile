java:=\
src/jeu/Jeu.java\
src/jeu/Sequence.java\
src/jeu/ChargeurTexture.java\
src/slide/Joueur.java\
src/slide/Slide.java\
src/slide/cases/Glace.java\
src/slide/cases/Sol.java\
src/slide/cases/Fleche.java\
src/slide/cases/Case.java\
src/slide/cases/Rocher.java\
src/slide/cases/Arrivee.java\
src/slide/MenuPauseSlide.java\
src/slide/Entite.java\
src/slide/Fin.java\
src/slide/NewEventGame.java\
src/slide/Plateau.java

classpath:=../jsfml.jar

../Slide.jar: $(java) $(ressources)
	mkdir archives
	javac -cp $(classpath) $(java) -d archives
	unzip ../jsfml.jar -d archives
	echo "Class-Path: .\nMain-Class: slide.Slide">MANIFEST.MF
	cat archives/META-INF/MANIFEST.MF>>MANIFEST.MF
	rm -r archives/META-INF
	jar cvfm ../Slide.jar MANIFEST.MF -C archives .
	jar uvf ../Slide.jar -C src/ ressources
	rm MANIFEST.MF
	rm -r archives

clean:
	find .. -name Slide.jar -exec rm {} \;
