package com.coelhovictor.tictactoe.guis;

import com.coelhovictor.localstorage.objs.LocalStorage;
import com.coelhovictor.tictactoe.Main;
import com.coelhovictor.tictactoe.objs.Difficulty;
import com.coelhovictor.tictactoe.objs.Scoreleader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author VictorCoelho
 */
public class Scoreleaders extends javax.swing.JFrame {
    
    /** 
     * Class contructor.
     */
    public Scoreleaders() {
        
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
        
        javax.swing.JPanel headerPanel = new javax.swing.JPanel();
        javax.swing.JPanel bottomPanel = new javax.swing.JPanel();
        javax.swing.JButton backButton = new javax.swing.JButton();
        JLabel centerLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TicTacToe");
        setBackground(new java.awt.Color(102, 102, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        /**
         * Header
         */
        
        centerLabel.setText("Scoreleaders");
        centerLabel.setFont(new java.awt.Font("Mukta Medium", 1, 18));
        centerLabel.setForeground(Color.WHITE);
        centerLabel.setHorizontalAlignment(JLabel.CENTER);
        centerLabel.setVerticalAlignment(JLabel.CENTER);
        
        JPanel topPanel = new JPanel(); 
        topPanel.setBackground(Color.BLACK);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(centerLabel, "Center");
        topPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(topPanel, BorderLayout.CENTER);
        
        add(headerPanel, BorderLayout.NORTH);
        
        /**
         * Center
         */
        
        JPanel centerPanel = new JPanel(); 
        centerPanel.setBorder(new EmptyBorder(30, 50, 45, 50));
        centerPanel.setLayout(new BorderLayout());
        
        String[] columnNames = {
            "Nick",
            "Wins",
            "Defeats"
        };
        
        /**
         * Get all scoreleaders
         */
        
        LocalStorage scoreLeadersDB = Main.getUtil().getScoreLeadersDB();
        List<Scoreleader> ls = scoreLeadersDB.findAll();
        
        Object[][] data = new Object[ls.size()][3];
        
        for(int i = 0; i < ls.size(); i++) {
            
            Scoreleader scoreleader = ls.get(i);
            Object[] score = {
                scoreleader.getPlayerNick(),
                scoreleader.getWins(),
                scoreleader.getDefeats()
            };
            data[i] = score;
        }
        
        JTable table = new JTable(data, columnNames) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {                
                    return false;               
            };
        };
        table.setFillsViewportHeight(true);
        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        
        centerPanel.add(table.getTableHeader(), BorderLayout.NORTH);
        centerPanel.add(table, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);
        
        /**
         * Bottom panel
         */
        
        backButton.setText("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                /**
                 * Back home
                 */

                back();

            }
        });
        
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        /**
         * Finish
         */
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    /**
     * Back to home.
     */
    private void back() {
        this.dispose();
        new Home(true, Difficulty.EASY);
    }
    
}
