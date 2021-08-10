package com.coelhovictor.tictactoe.guis;

import com.coelhovictor.tictactoe.Main;
import com.coelhovictor.tictactoe.objs.Game;
import com.coelhovictor.tictactoe.objs.GameAction;
import com.coelhovictor.tictactoe.objs.Player;
import com.coelhovictor.tictactoe.objs.Scoreboard;
import com.coelhovictor.tictactoe.objs.Spot;
import com.coelhovictor.tictactoe.objs.SpotType;
import com.coelhovictor.tictactoe.objs.TurnType;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author VictorCoelho
 */
public class Play extends javax.swing.JFrame {
    
    /**
     * Current game.
     */
    private final Game game;
    
    /**
     * Current game timer.
     */
    private final Timer gameTimer;
    
    /**
     * Player score.
     */
    private JLabel playerScore;
    
    /**
     * CPU score.
     */
    private JLabel cpuScore;
    
    /**
     * Turn information.
     */
    private JLabel turnLabel;
    
    /**
     * Game <code>JPanel</code>.
     */
    private JPanel gamePanel;
    
    /** 
     * Class contructor.
     * 
     * @param game current game
     */
    public Play(Game game) {
        this.game = game;
        
        /**
         * Inicialize screen
         */
        inicialize();
      
        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                
                /**
                 * Update the current game
                 */
                
                updateGame();
                
            }
        };
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(repeatedTask, 1000, 1000);
        gameTimer = timer;
        
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
        javax.swing.JButton restartButton = new javax.swing.JButton();
        JLabel centerLabel = new javax.swing.JLabel();
        playerScore = new javax.swing.JLabel();
        cpuScore = new javax.swing.JLabel();
        turnLabel = new javax.swing.JLabel();
        gamePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TicTacToe");
        setBackground(new java.awt.Color(102, 102, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        /**
         * Header
         */
        
        centerLabel.setText(Main.getSession().getPlayer().getNick() + " vs CPU");
        centerLabel.setFont(new java.awt.Font("Dialog", 1, 24));
        centerLabel.setForeground(Color.WHITE);
        
        turnLabel.setText(turnIndicator());
        turnLabel.setFont(new java.awt.Font("Dialog", 1, 18));
        
        playerScore.setText(Main.getSession().getScoreboard().getPlayerScore() + "");
        playerScore.setFont(new java.awt.Font("Dialog", 1, 20));
        playerScore.setBorder(new EmptyBorder(10, 10, 10, 10));
        playerScore.setForeground(Color.WHITE);
        
        cpuScore.setText(Main.getSession().getScoreboard().getCPUScore() + "");
        cpuScore.setFont(new java.awt.Font("Dialog", 1, 20));
        cpuScore.setBorder(new EmptyBorder(10, 10, 10, 10));
        cpuScore.setForeground(Color.WHITE);
        
        JPanel topPanel = new JPanel(); 
        topPanel.setBackground(Color.BLACK);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(playerScore, BorderLayout.WEST);
        centerLabel.setHorizontalAlignment(JLabel.CENTER);
        centerLabel.setVerticalAlignment(JLabel.CENTER);
        topPanel.add(centerLabel, "Center");
        topPanel.add(cpuScore, BorderLayout.EAST);
        
        JPanel downPanel = new JPanel(); 
        downPanel.setLayout(new BorderLayout());
        downPanel.setBackground(Color.GRAY);
        turnLabel.setHorizontalAlignment(JLabel.CENTER);
        turnLabel.setVerticalAlignment(JLabel.CENTER);
        turnLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        downPanel.add(turnLabel, BorderLayout.CENTER);
        
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(topPanel, BorderLayout.NORTH);
        headerPanel.add(downPanel, BorderLayout.SOUTH);
        
        add(headerPanel, BorderLayout.NORTH);
        
        /**
         * Game container
         */
        
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setPreferredSize(new Dimension(380, 380));
        
        for(int rows = 0; rows < 3; rows++) {
            for(int columns = 0; columns < 3; columns++) {
                
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(120, 120));
                button.setBackground(Color.WHITE);
                button.setBorder(new LineBorder(Color.BLACK, 2));
                button.setFont(new java.awt.Font("Dialog", 1, 50));
                
                final int row = rows;
                final int column = columns;
                
                String keySpot = keySpot(row, column);
                this.game.setSpot(keySpot, new Spot(row, column));
                
                button.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        
                        /**
                         * Game action
                         */
                        
                        action(row, column, TurnType.PLAYER);
                        
                    }
                });
                
                switch(keySpot) {
                    case "0@0":
                      spotButtom0_0 = button;
                      break;
                    case "0@1":
                      spotButtom0_1 = button;
                      break;
                    case "0@2":
                      spotButtom0_2 = button;
                      break;
                    case "1@0":
                      spotButtom1_0 = button;
                      break;
                    case "1@1":
                      spotButtom1_1 = button;
                      break;
                    case "1@2":
                      spotButtom1_2 = button;
                      break;
                    case "2@0":
                      spotButtom2_0 = button;
                      break;
                    case "2@1":
                      spotButtom2_1 = button;
                      break;
                    case "2@2":
                      spotButtom2_2 = button;
                      break;
                }
                
                gamePanel.add(button);
                
            }
        }
        
        add(gamePanel, BorderLayout.CENTER);
        
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
        
        restartButton.setText("Restart");
        restartButton.setPreferredSize(new Dimension(100, 30));
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                /**
                 * Restart the game
                 */

                restart();

            }
        });
        
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(backButton, BorderLayout.WEST);
        bottomPanel.add(restartButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        
        /**
         * Finish
         */
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    /**
     * Execute a game action.
     *
     * @param row num of rows
     * @param column num of columns
     * @param turnType the action <code>TurnType</code>
     * @return <code>SpotType</code>
     */
    public SpotType action(int row, int column, TurnType turnType) {
        
        Player player = null;
        if(turnType == TurnType.PLAYER) {
            player = Main.getSession().getPlayer();
        }
        
        GameAction action = new GameAction(player, turnType, row, column);
        SpotType spotType = this.game.action(action);
        
        if(spotType != null) {
        
            String spotText = spotType.toString();
            JButton button = foundButton(action.getRow(), action.getColumn());
            button.setText(spotText);
            
            /**
             * Check if has a winner
             */
            
            SpotType winner = this.game.checkWinner(action);
            
            turnLabel.setText(turnIndicator());
            
            if(winner != null) {
            
                /**
                 * Update winner spots color
                 */

                for(Spot ls : this.game.getWinnerSpots()) {

                    JButton buttonWinner = foundButton(ls.getRow(), ls.getColumn());
                    if(winner == SpotType.X) {
                        buttonWinner.setBackground(Color.GREEN);
                    } else {
                        buttonWinner.setBackground(Color.RED);
                    }
                    buttonWinner.setForeground(Color.WHITE);

                }
                
                /**
                 * Update scoreboard
                 */
                
                Scoreboard score = Main.getSession().getScoreboard();
                if(winner == SpotType.X) {
                    score.addPlayer();
                    playerScore.setText(score.getPlayerScore() + "");
                } else {
                    score.addCPU();
                    cpuScore.setText(score.getCPUScore() + "");
                }

                return null;
            } else {
                
                /**
                 * Check if is a draw
                 */
                
                boolean draw = this.game.checkDraw();
                if(draw) {
                    
                    /**
                     * Update draw status
                     */
                    
                    turnLabel.setText("Draw!");
                    
                }
                
            }
            
        }
        
        return spotType;
    }
    
    /**
     * Back to home.
     */
    private void back() {
        this.gameTimer.cancel();
        this.dispose();
        new Home();
    }
    
    /**
     * Restart the game.
     */
    private void restart() {
        this.gameTimer.cancel();
        this.dispose();
        new Play(new Game());
    }
    
    /**
     * Execute a CPU action.
     */
    private void updateGame() {
        
        if(this.game.isLive()) {
            if(this.game.getTurnType() == TurnType.CPU) {
                playCPU();
            }
        }
        
    }
    
    /**
     * Build the CPU action.
     */
    private void playCPU() {
        
        List<Spot> ls = this.game.avaliableSpots();
        
        int row = 0;
        int column = 0;
        
        if(!ls.isEmpty()) {
            
            /*
                CPU chooses the best spot
            */
            
            Random random = new Random();
            int r = random.nextInt(ls.size());
            
            Spot target = ls.get(r);
            if(target != null) {
                row = target.getRow();
                column = target.getColumn();
            }
            
        }
        
        action(row, column, TurnType.CPU);
        
    }
    
    /**
     * Returns the game turn indicator.
     * 
     * @return <code>String</code> the turn indicator
     */
    private String turnIndicator() {
        SpotType type = this.game.getWinner();
        if(type != null) {
            if(type == SpotType.X) {
                return Main.getSession().getPlayer().getNick() + " won!";
            }
            return "CPU won!";
        }
        TurnType typeTurn = this.game.getTurnType();
        if(typeTurn == TurnType.PLAYER) {
            return Main.getSession().getPlayer().getNick() + "'s turn";
        }
        return "CPU's turn";
    }
    
    /**
     * Returns the spot key.
     * 
     * @param row num of the row
     * @param column num of the column
     * @return <code>String</code> the key spot
     */
    private String keySpot(int row, int column) {
        return row + "@" + column;
    }
    
    /**
     * Returns the target button.
     * 
     * @param row num of the row
     * @param column num of the column
     * @return <code>JButton</code> the target button
     */
    private JButton foundButton(int row, int column) {
        switch(keySpot(row, column)) {
            case "0@0":
              return spotButtom0_0;
            case "0@1":
              return spotButtom0_1;
            case "0@2":
              return spotButtom0_2;
            case "1@0":
              return spotButtom1_0;
            case "1@1":
              return spotButtom1_1;
            case "1@2":
              return spotButtom1_2;
            case "2@0":
              return spotButtom2_0;
            case "2@1":
              return spotButtom2_1;
            case "2@2":
              return spotButtom2_2;
        }
        return null;
    }
    
    /**
     * All game <code>JButton</code>.
     */
    private javax.swing.JButton spotButtom0_0;
    private javax.swing.JButton spotButtom0_1;
    private javax.swing.JButton spotButtom0_2;
    private javax.swing.JButton spotButtom1_0;
    private javax.swing.JButton spotButtom1_1;
    private javax.swing.JButton spotButtom1_2;
    private javax.swing.JButton spotButtom2_0;
    private javax.swing.JButton spotButtom2_1;
    private javax.swing.JButton spotButtom2_2;
    
}
