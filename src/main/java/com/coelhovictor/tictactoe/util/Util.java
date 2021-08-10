package com.coelhovictor.tictactoe.util;

import com.coelhovictor.localstorage.controllers.LocalStorageController;
import com.coelhovictor.localstorage.objs.LocalStorage;
import com.coelhovictor.tictactoe.objs.Scoreleader;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

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
     * Images path.
     */
    private final String imagesPath = "assets"+File.separator+"images"+File.separator;
    
    /**
     * Fonts path.
     */
    private final String databasePath = "C:\\Users\\Pichau\\Desktop\\database\\";
    
    /**
     * GraphicsEnvironment.
     */
    private final GraphicsEnvironment ge;
    
    /**
     * Scoreleaders database.
     */
    private final LocalStorage scoreLeadersDB;

    /**
     * Image icon.
     */
    private final ImageIcon img;
    
    /**
     * Class constructor.
     */
    public Util() {
        this.ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        /**
         * Inicialize image icon
         */
        
        this.img = new ImageIcon(imagesPath + "icon.png");
        
        /**
         * Inicialize database
         */
        
        LocalStorageController.inicialize(this.databasePath);
        
        /**
         * Inicialize scoreleaders database
         */
        
        this.scoreLeadersDB = new LocalStorage("scoreleaders", Scoreleader.class);
        
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
            ge.registerFont(textaAltRegular);
            
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    /**
     * Returns the scoreleaders database
     * 
     * @return <code>LocalStorage</code> the scoreleaders database
     */
    public LocalStorage getScoreLeadersDB() { return this.scoreLeadersDB; }
    
    /**
     * Returns the iimage icon.
     * 
     * @return <code>ImageIcon</code> image icon
     */
    public ImageIcon getImageIcon() { return this.img; }
    
}
