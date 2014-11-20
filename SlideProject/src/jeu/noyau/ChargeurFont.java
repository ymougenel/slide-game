package jeu.noyau;

import java.io.IOException;

import org.jsfml.graphics.Font;

public enum ChargeurFont {
			Stocky("stocky.ttf"),
			Orange("orangejuice.ttf");
			
			private ChargeurFont (String police){
				this.nomPolice = police;
			}
			private String nomPolice;
			private Font font = null;
			public Font getFont() {
				if (font == null){
					font = new Font();
					try {
						font.loadFromStream(ChargeurFont.class.getResourceAsStream("/ressources/polices/"+nomPolice));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
				return font;
			}
}
