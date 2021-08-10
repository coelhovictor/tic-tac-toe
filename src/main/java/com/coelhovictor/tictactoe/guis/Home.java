package com.coelhovictor.tictactoe.guis;

import com.coelhovictor.tictactoe.Main;
import com.coelhovictor.tictactoe.objs.Game;
import com.coelhovictor.tictactoe.objs.Player;
import com.coelhovictor.tictactoe.objs.Session;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
     * Class contructor.
     */
    public Home() {
        
        /**
         * Inicialize screen
         */
        inicialize();
    }
    
    /**
     * Inicialize and build the home screen.
     */
    private void inicialize() {
      
        /**
         * Page props
         */
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TicTacToe");
        setBackground(new java.awt.Color(102, 102, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        /**
         * Header
         */

        JLabel titleLabel = new javax.swing.JLabel();
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 28));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("TicTacToe");
        titleLabel.setForeground(Color.WHITE);
        
        JLabel authorLabel = new javax.swing.JLabel();
        authorLabel.setFont(new java.awt.Font("Dialog", 1, 14));
        authorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        authorLabel.setText("Author: VictorCoelho");
        authorLabel.setForeground(Color.WHITE);
        
        JPanel headerPanel = new JPanel(); 
        headerPanel.setBackground(Color.BLACK);
        headerPanel.setLayout(new GridLayout(2, 1, 0, -40));
        headerPanel.add(titleLabel);
        headerPanel.add(authorLabel);
        headerPanel.setPreferredSize(new Dimension(350, 105));
        
        add(headerPanel, BorderLayout.NORTH);
        
        /**
         * Form
         */

        JLabel nickLabel = new javax.swing.JLabel();
        JTextField nickField = new javax.swing.JTextField();
        JButton startButton = new javax.swing.JButton();
        
        nickLabel.setText("Nick:");
        nickLabel.setFont(new java.awt.Font("Dialog", 1, 14));

        nickField.setBackground(new java.awt.Color(204, 204, 204));
        nickField.setText("coelho");
        nickField.setPreferredSize(new Dimension(120, 30));
        
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
        
        JPanel formPanel = new JPanel(); 
        formPanel.setLayout(new GridLayout(3, 1, 0, 5));
        formPanel.add(nickLabel);
        formPanel.add(nickField);
        formPanel.add(startButton);
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

        Game game = new Game();

        new Play(game);
        
    }
    
}
