package game;

import city.cs.engine.DebugViewer;
import city.cs.engine.SoundClip;
import city.cs.engine.UserView;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Will Dibble
 * @version 1.13.2
 * @since 1.0
 * This class is Game.
 * This class initialises the main game.
 * It does so through building the players view, backgrounds and GUI.
 */

public class Game {

    private GameLevel world;

    //A graphical display of the world (a specialised JPanel).
    private UserView view;

    public static int level;

    private Controller1 controller;

    private static SoundClip gameMusic;

    private DebugViewer debugView;

    public static final Color skyColour = new Color(167, 180, 255);

    /**
     * Creates the game world.
     */
    public Game() {

        // make the world
        level = 0;
        world = new Level0();
        world.populate(this);

        // make a view
        view = new UserView(world, 800, 800);

        //Initialise Background
        view = new Background(world, 800, 800);

        // display the view in a frame
        JFrame frame = new JFrame("Multi-level game");

        ControlPanel buttons = new ControlPanel(this);
        frame.add(buttons.getMainPanel(), BorderLayout.SOUTH);

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        // get keyboard focus
        frame.requestFocus();
        // give keyboard focus to the frame whenever the mouse enters the view
        view.addMouseListener(new GiveFocus(frame));

        // In Game constructor, store single controller instance
        controller = new Controller1(world.getPlayer(), world);
        frame.addKeyListener(controller);

        //Step listener to allow for camera to track players position
        world.addStepListener(new Tracker(view, world.getPlayer()));

        // add a wide angle view of the world
        /*UserView wideView = new UserView(world, 500, 100);
        wideView.setZoom(4);
        wideView.setBackground(skyColour);
        frame.add(wideView, BorderLayout.SOUTH);*/

        // start!
        world.start();

        //Create a Scanner object, after username is entered, write the users time and name into a text file.
        //Scanner playerName = new Scanner(System.in);
        //System.out.println("Enter username");
        //Background.username = playerName.nextLine();
        Background.username = "Demo";

        try {
            if (saveGame.findUser() == -1) {
                System.out.println("Enjoy the game");
            } else {
                System.out.println("Welcome Back Player");

                saveGame.read();

                Game.level = saveGame.playerLevel - 1;

                Player1.setCoinCount(saveGame.playerScore);
                Player1.setHealthCount(saveGame.playerHealth);
                Background.myTimer = saveGame.playerTime;


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Background.usernameEntered = true;

    }

    /**
     * The player in the current level.
     */
    public Player1 getPlayer() {
        return world.getPlayer();
    }

    /**
     * Is the current level of the game finished?
     */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }


    /**
     * Advance to the next level of the game.
     */
    public void goNextLevel() {

        // Store old tracker to remove
        Tracker oldTracker = new Tracker(view, world.getPlayer());
        world.removeStepListener(oldTracker);

        int xHealth = Player1.getHealthCount();
        //int yCoin = Player1.getCoinCount();

        world.stop();

        level++;
        // get a new world
        if (level == 0) {
            world = new Level0();

        } else if (level == 1) {
            world = new Level1();
            //debugView = new DebugViewer(world,800,800);

        } else if (level == 2) {
            world = new Level2();

            //Initialise Music
            try {
                gameMusic.stop();
                gameMusic = new SoundClip("data/sounds/game2.wav");   // Open an audio input stream
                gameMusic.loop();  // Set it to loop playback
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }

            //debugView = new DebugViewer(world,800,800);
        } else if (level == 3) {
            world = new Level3();

            //Initialise Music
            try {
                gameMusic.stop();
                gameMusic = new SoundClip("data/sounds/game3.wav");   // Open an audio input stream
                gameMusic.loop();  // Set it to loop playback
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }

            //When the player gets past level 3 and their time is under 100 seconds, save their score to the leaderboard.
        } else if (level == 4) {
            if ((Background.myTimer / 100) < 100) {
                writeToFile.write(Background.username, Background.myTimer);
            }

            //Initialise Music
            try {
                gameMusic.stop();
                gameMusic = new SoundClip("data/sounds/game4.wav");   // Open an audio input stream
                gameMusic.loop();  // Set it to loop playback
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }
        }

        // fill it with bodies
        world.populate(this);
        Player1.setHealthCount(xHealth);
        //world.getPlayer().setCoinCount(yCoin);

        controller.setBody(world.getPlayer());
        //System.out.println("Here: " + controller.getPlayer());
        controller.setGameLevel(world);

        // show the new world in the view
        view.setWorld(world);

        //set the view to follow the player.
        world.addStepListener(new Tracker(view, world.getPlayer()));

        if (Controller1.jumpIncreased) {
            Controller1.decreaseJump();
            Controller1.jumpIncreased = false;
        }

        world.start();

    }

    /**
     * Retrieve the game world.
     *
     * @return the game world.
     */
    public GameLevel getWorld() {
        return world;
    }

    /**
     * Run the game.
     */
    public static void main(String[] args) {
        new Game();

        //Initialise Music
        try {
            gameMusic = new SoundClip("data/sounds/game1.wav");   // Open an audio input stream
            gameMusic.loop();  // Set it to loop playback
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

    }
}
