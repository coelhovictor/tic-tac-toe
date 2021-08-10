package com.coelhovictor.tictactoe.guis;

import com.coelhovictor.tictactoe.Main;
import com.coelhovictor.tictactoe.objs.Difficulty;
import com.coelhovictor.tictactoe.objs.Game;
import com.coelhovictor.tictactoe.objs.Player;
import com.coelhovictor.tictactoe.objs.Session;
import com.coelhovictor.tictactoe.objs.SpotType;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author VictorCoelho
 */
public class Home extends javax.swing.JFrame {
    
    /**
     * The spot type selector.
     */
    private JComboBox spotTypeSelector;
    
    /**
     * The start playing checkbox.
     */
    private JCheckBox startPlayingCheckbox;
    
    /**
     * The difficulty selector.
     */
    private JComboBox difficultySelector;
    
    /** 
     * Class contructor.
     * 
     * @param startPlaying if player start playing
     * @param difficulty the game difficulty
     */
    public Home(boolean startPlaying, Difficulty difficulty) {
        
        /**
         * Inicialize screen
         */
        inicialize(startPlaying, difficulty);
    }
    
    /**
     * Inicialize and build the home screen.
     * 
     * @param startPlaying if player start playing
     * @param difficulty the game difficulty
     */
    private void inicialize(boolean startPlaying, Difficulty difficulty) {
      
        /**
         * Page props
         */
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TicTacToe");
        setIconImage(Main.getUtil().getImageIcon().getImage());
        setBackground(new java.awt.Color(102, 102, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        /**
         * Header
         */

        JLabel titleLabel = new javax.swing.JLabel();
        titleLabel.setFont(new java.awt.Font("Mukta Medium", 1, 32));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("TicTacToe");
        titleLabel.setForeground(Color.WHITE);
        
        JLabel authorLabel = new javax.swing.JLabel();
        authorLabel.setFont(new java.awt.Font("Texta Alt W00 Regular", 1, 14));
        authorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        authorLabel.setText("github.com/CoelhoVictor");
        authorLabel.setForeground(Color.WHITE);
        
        JPanel headerPanel = new JPanel(); 
        headerPanel.setBackground(Color.BLACK);
        headerPanel.setLayout(new GridLayout(2, 1, 0, -50));
        headerPanel.add(titleLabel);
        headerPanel.add(authorLabel);
        headerPanel.setPreferredSize(new Dimension(350, 105));
        
        add(headerPanel, BorderLayout.NORTH);
        
        /**
         * Form
         */

        JTextField nickField = new javax.swing.JTextField();
        
        nickField.setBackground(new java.awt.Color(204, 204, 204));
        nickField.setText("coelho");
        nickField.setPreferredSize(new Dimension(120, 30));
        
        JButton startButton = new javax.swing.JButton();
        startButton.setText("Play!");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                /**
                 * Start the game
                 */
                
                String targetNick = nickField.getText();
                if(!targetNick.isEmpty()) {
                    
                    game(targetNick);
                    
                }
            }
        });
        
        JButton scoreLeadersButton = new javax.swing.JButton();
        scoreLeadersButton.setText("Scoreleaders");
        scoreLeadersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                /**
                 * Open scoreleaders screen
                 */
                
                scoreleaders();
            }
        });
        
        String[] ls = { "Random", SpotType.X.toString(), SpotType.O.toString() };
        spotTypeSelector = new JComboBox(ls);
        spotTypeSelector.setSelectedIndex(0);
        
        startPlayingCheckbox = new JCheckBox();
        startPlayingCheckbox.setText("Start playing");
        startPlayingCheckbox.setSelected(startPlaying);
        
        String[] lsDiff = { Difficulty.EASY.toString(), Difficulty.MEDIUM.toString(), Difficulty.HARD.toString() };
        difficultySelector = new JComboBox(lsDiff);
        difficultySelector.setSelectedIndex(difficulty.ordinal());
        
        JPanel formPanel = new JPanel(); 
        formPanel.setLayout(new GridLayout(9, 1, 0, 5));
        formPanel.add(new JLabel("Nick:"));
        formPanel.add(nickField);
        formPanel.add(new JLabel("Symbol:"));
        formPanel.add(spotTypeSelector);
        formPanel.add(new JLabel("Difficulty:"));
        formPanel.add(difficultySelector);
        formPanel.add(startPlayingCheckbox);
        formPanel.add(startButton);
        formPanel.add(scoreLeadersButton);
        formPanel.setBorder(new EmptyBorder(30, 50, 45, 50));
        
        add(formPanel, BorderLayout.CENTER);
        
        /**
         * Finish
         */
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    /**
     * Start the game.
     * 
     * @param targetNick the player nick
     */
    public void game(String targetNick) {
        
        this.dispose();
        
        Player p = new Player(targetNick);
        Session session = new Session(p);
        Main.setSession(session);

        /**
         * Target player symbol
         */
        
        SpotType targetSpotType = SpotType.X;
        
        String index = (String)spotTypeSelector.getSelectedItem();
        if(index.equalsIgnoreCase("RANDOM")) {
            Random random = new Random();
            if(random.nextInt(2) == 0) {
                targetSpotType = SpotType.X;
            } else {
                targetSpotType = SpotType.O;
            }
        } else {
            SpotType target = SpotType.valueOf(index);
            if(target != null) {
                targetSpotType = target;
            }
        }
        
        /**
         * If player start playing
         */
        
        boolean startPlaying = startPlayingCheckbox.isSelected();
        
        /**
         * Game difficulty
         */
        
        Difficulty targetDifficulty = Difficulty.EASY;
        
        String indexDiff = (String)difficultySelector.getSelectedItem();
        Difficulty target = Difficulty.valueOf(indexDiff);
        if(target != null) {
            targetDifficulty = target;
        }
        
        Game game = new Game(targetSpotType, startPlaying, targetDifficulty);

        new Play(game);
        
    }
    
    /**
     * Open scoreleaders screen
     */
    private void scoreleaders() {
        this.dispose();
        new Scoreleaders();
    }
    
}
