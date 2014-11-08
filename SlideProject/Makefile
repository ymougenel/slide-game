java:=$(shell find src/jeu -name "*.java")
ressources:=$(shell find src/ressources)
classpath:=../jsfml.jar:.
main:=jeu.slide.Slide

../Slide.jar: $(java) $(ressources)
	mkdir archives
	javac -cp $(classpath) $(java) -d archives
	unzip ../jsfml.jar -d archives
	echo "Class-Path: .\nMain-Class: $(main)">MANIFEST.MF
	cat archives/META-INF/MANIFEST.MF>>MANIFEST.MF
	rm -r archives/META-INF
	jar cvfm ../Slide.jar MANIFEST.MF -C archives .
	jar uvf ../Slide.jar -C src/ ressources
	rm MANIFEST.MF
	rm -r archives

clean:
	find .. -name Slide.jar -exec rm {} \;
