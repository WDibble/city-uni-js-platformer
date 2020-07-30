package game;

import city.cs.engine.UserView;
import org.jbox2d.common.Vec2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.2
 *  This class is Background.
 *  This class sets every background, on screen text (including timer) and scoreboard.
 */

public class Background extends UserView {

    //All the images for use in the foreground and the background.
    private BufferedImage bg;
    private BufferedImage bg2;
    private BufferedImage bg3;
    private BufferedImage fg;
    private BufferedImage win;
    private BufferedImage dead;
    private BufferedImage scoreBoard;

    private BufferedImage bulletBillSelect;
    private BufferedImage bulletSelect;
    private BufferedImage arrow;
    private BufferedImage health;
    private BufferedImage black;

    //Used to make sure the user has gone past level 0 before displaying the GUI.
    public static boolean usernameEntered = false;

    public static String username;

    public static int myTimer = 0;

    private GameLevel world;

    /**
     * Generate the background using a buffered image
     *
     * @param w the world.
     * @param width width of the background.
     * @param height heght of th background.
     */
    public Background(GameLevel w, int width, int height) {
        super(w, 800, 800);
        this.world = w;
        //this.gameLevel = gameLevel;
        try {
            //All images being read from their respective image files and being assigned to variables.
            bg = ImageIO.read(new File("data/bg.png"));
            bg2 = ImageIO.read(new File("data/bg2.png"));
            bg3 = ImageIO.read(new File("data/bg3.png"));
            fg = ImageIO.read(new File("data/clouds.png"));
            win = ImageIO.read(new File("data/win.jpg"));
            dead = ImageIO.read(new File("data/dead.jpg"));
            scoreBoard = ImageIO.read(new File("data/scoreBoard.png"));
            bulletBillSelect = ImageIO.read(new File("data/GUI/billSelect.png"));
            bulletSelect = ImageIO.read(new File("data/GUI/bulletSelect.png"));
            arrow = ImageIO.read(new File("data/GUI/arrow.png"));
            health = ImageIO.read(new File("data/GUI/health.png"));
            black = ImageIO.read(new File("data/GUI/black.jpg"));


        } catch (IOException ex) {
            System.out.println("System Error");
        }
    }

    //Paint the background image to the background depending on the level.

    /**
     * Paint the background.
     *
     * @param g Paint the graphics.
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        super.paintBackground(g);
        if (Game.level == 0) {
            g.drawImage(bg, 0, 0, this);
        } else if (Game.level == 1) {
            g.drawImage(bg, 0, 0, this);
        } else if (Game.level == 2) {
            g.drawImage(bg2, 0, 0, this);
        } else if (Game.level == 3) {
            g.drawImage(bg3, 0, 0, this);
        }

    }

    /**
     * Paint the foreground.
     *
     * @param g Paint the graphics.
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);
        //When level is 0, show the tutorial screen to teach the player how the game works.
        if (Game.level == 0) {
            if (usernameEntered) {
                //Instructions for player on first load.
                g.setFont(new Font("Arial", Font.PLAIN, 30));
                g.drawString("Will's Game", 20, 40);
                g.drawString("Collect 10 Points In Each Level To Progress", 20, 80);
                g.drawString("Enemy Collisions Will Damage Your Health", 20, 120);
                g.drawString("Use WASD To Control Your Character", 20, 160);
                g.drawString("Jump Into The Portal To Start The Game", 20, 200);
            } else {
                //At the beginning of the gae the player is required to enter their username.
                g.setFont(new Font("Arial", Font.PLAIN, 30));
                g.drawImage(black, 0, 0, 800, 800, this);
                g.drawString("Please Enter Your Name Below", 200, 350);
                g.drawString("Then Return To This Page", 230, 450);

            }

        //When the player gets past level 3, show the player they have won the game.
        } else if (Game.level == 4) {
            world.stop();
            g.drawImage(win, -300, 0, 1440, 810, this);
        }

        //When the player runs out of health, stop the game and display a death screen.
        else if (Game.level == 6) {
            world.stop();
            g.drawImage(dead, -270, 0, 1340, 810, this);
        }

        //When the scoreboard button is pressed, sort the text file into score order
        //and display the top 10 scores on the screen.
        else if (ControlPanel.scoreboardShow) {
            g.setFont(new Font("Arial", Font.PLAIN, 35));
            new FileSorter("filename.txt");
            g.drawImage(scoreBoard, 250, 100, 300, 600, this);
            g.drawString("SCOREBOARD:", 275, 150);
            g.setFont(new Font("Arial", Font.PLAIN, 25));

            try {
                FileStrings strings = new FileStrings("filename.txt");

                for (int i = 1; i < 11; i++) {
                    g.drawString(writeToFile.read(i), 275, 160 + (i * 50));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //On all scoring levels, display the score, health, level number and the timer.
        else {
            g.drawImage(fg, -200, -400, 1522, 650, this);

            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("SCORE: " + Player1.getCoinCount() + "/10", (getWidth() / 2) - 55, 40);

            if (Player1.getHealthCount() == 0) {
                g.drawString("HEALTH: " + (Player1.getHealthCount() + 1), 20, 750);
            } else {
                g.drawString("HEALTH: " + Player1.getHealthCount(), 20, 750);
            }

            g.drawImage(health, 20, 775, (Player1.getHealthCount() * 5) + 20, 5, this);

            if (Level0.introComplete) {
                g.drawString("LEVEL " + Game.level, 20, 40);
            } else {
                g.drawString("LEVEL " + (Game.level + 1), 20, 40);
            }

            //My in game timer
            Timer timer = new Timer(1000, new MyTimerActionListener());
            timer.setInitialDelay(100);
            timer.start();
            myTimer++;
            g.drawString("TIME: " + myTimer / 100, 20, 80);

            g.drawString("User: " + username, 20, 120);

            //As long as the player is on level 2 or above, allow the player to use weapons.
            if (Level0.introComplete) {
                if (Game.level > 1) {
                    if (Controller1.bulletType == 1) {
                        g.drawImage(bulletSelect, 575, 25, 200, 100, this);
                        g.drawImage(arrow, 600, 130, 50, 30, this);
                    } else if (Controller1.bulletType == 2) {
                        g.drawImage(bulletBillSelect, 575, 25, 200, 100, this);
                        g.drawImage(arrow, 700, 130, 50, 30, this);
                    }
                }

                if (Game.level == 2) {

                    g.setFont(new Font("Arial", Font.PLAIN, 20));
                    g.drawString("Use SPACEBAR To Fire Bullets To Kill Enemies!", 20, 145);
                }

                //Whenever the timers modulus 500 is 0 and the player is above level 1, shoot fireballs from the Robots.
                if (myTimer % 500 == 0 && Game.level > 1) {
                    //System.out.println("Shoot");

                    //Uses the assigned arrays the create the fireballs at the randomised locations of the robots.
                    for (int i = 0; i < GameLevel.numOfEnemy2; i = i + 1) {
                        Fireball fireball = new Fireball(Controller1.gameLevel);

                        if (GameLevel.enemy2Position[i] == 2) {
                            fireball.setPosition(new Vec2(GameLevel.enemy2ArrayX[i] + 2.0f, GameLevel.enemy2ArrayY[i]));
                            fireball.setLinearVelocity(new Vec2(10.0f, 0f));
                        } else {
                            fireball.setPosition(new Vec2(GameLevel.enemy2ArrayX[i] - 2.0f, GameLevel.enemy2ArrayY[i]));
                            fireball.setLinearVelocity(new Vec2(-10.0f, 0f));
                        }

                        fireball.removeAllImages();
                        fireball.addImage(Fireball.fireballImage);

                        //Collision listener so the fireballs deal damage to the player.
                        fireball.addCollisionListener(new FireballCollision(Controller1.gameLevel.getPlayer()));
                    }
                }
            }

        }

    }

    /**
     * Allows the timer to start.
     */
    static class MyTimerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }

}
