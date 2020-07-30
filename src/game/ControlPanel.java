package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.10
 * This class is ControlPanel.
 * This class has all the variables and methods needed to show the GUI on screen and allow the player to interact with it.
 * It allows the player to quit, reset, pause, show scoreboard and save the game.
 */

public class ControlPanel {
    private JPanel mainPanel;
    private JButton quitButton;
    private JButton resetButton;
    private JButton pauseButton;
    private JButton scoreboard;
    private JButton save;

    private int gamePaused = 0;

    public static boolean scoreboardShow = false;

    /**
     * Allows interactive buttons in the GUI to be pressed by the user.
     *
     * @param game the game.
     */
    public ControlPanel(Game game) {
        quitButton.addActionListener(new ActionListener() {
            /**
             * Quits the Game.
             *
             * @param e Action Event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        resetButton.addActionListener(new ActionListener() {
            /**
             * Resets the Game.
             *
             * @param e Action Event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.level = -1;
                game.getPlayer().setHealthCount(100);
                Background.myTimer = 0;
                GameLevel.enemy2ArrayY = new float[16];
                GameLevel.enemy2ArrayX = new float[16];
                GameLevel.numOfEnemy2 = 0;
                game.goNextLevel();
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            /**
             * Pauses the Game.
             *
             * @param e Action Event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gamePaused == 0) {
                    game.getWorld().stop();
                    gamePaused = 1;
                } else {
                    gamePaused = 0;
                    game.getWorld().start();
                }
            }
        });
        scoreboard.addActionListener(new ActionListener() {
            /**
             * Shows the ScoreBoard.
             *
             * @param e Action Event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                scoreboardShow = !scoreboardShow;
            }
        });
        save.addActionListener(new ActionListener() {
            /**
             * Saves the Game.
             *
             * @param e Action Event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (saveGame.findUser() == -1) {
                        saveGame.write(Background.username, Game.level, Player1.getCoinCount(), Background.myTimer, Player1.getHealthCount(), GameLevel.p1.getPosition().x, GameLevel.p1.getPosition().y);
                    } else {
                        System.out.println("You only get 1 save!");
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Gets the GUI.
     *
     * @return the mainPanel buttons.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

}
