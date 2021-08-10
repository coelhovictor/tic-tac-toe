package com.coelhovictor.tictactoe.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author VictorCoelho
 */
public class Util {
    
    /**
     * Fonts path.
     */
    private final String fontsPath = "assets"+File.separator+"fonts"+File.separator;
    
    /**
     * GraphicsEnvironment.
     */
    private final GraphicsEnvironment ge;
    
    /**
     * Class constructor.
     */
    public Util() {
        this.ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    }
    
    /**
     * Inicialize all fonts.
     */
    public void setupFonts() {
        
        try {
            
            /**
             * MuktaMedium 
             */
            
            Font muktaMedium = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File(fontsPath + "Mukta-Medium.ttf"))
                    .deriveFont(12f);
            ge.registerFont(muktaMedium);
            
            /**
             * RobotoRegular 
             */
            
            Font robotoRegular = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File(fontsPath + "Roboto-Regular.ttf"))
                    .deriveFont(12f);
            ge.registerFont(robotoRegular);
            
            /**
             * TextaAltRegular 
             */
            
            Font textaAltRegular = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File(fontsPath + "Texta-Alt-Regular.ttf"))
                    .deriveFont(12f);
            System.out.println(textaAltRegular.getName());
            ge.registerFont(textaAltRegular);
            
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
