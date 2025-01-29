package game;

import city.cs.engine.SoundClip;
import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.0
 * This class is Controller1.
 * This class sets the Inputs for controlling the player using W, A, S and D.
 */

public class Controller1 extends KeyAdapter {

    //Set jumping and walking speed of character
    private static float JUMPING_SPEED = 34;
    private static final float WALKING_SPEED = 10;

    private Game game;

    //private EnemyCollision enemy;
    private Walker body;
    private Bullet bulletBody;
    public static GameLevel gameLevel;
    private int bulletNumber = 0;
    public static int bulletType = 1;

    //Last body positions used for direction of bullet projectile fire.
    public static boolean lastBodyPosLeft = true;
    public static boolean lastBodyPosRight = false;

    public static boolean jumpIncreased = false;

    private static SoundClip jumpSound;

    /**
     * Relate the Controller to the Player and the Game.
     *
     * @param body the player.
     * @param gameLevel the game.
     */
    public Controller1(Walker body, GameLevel gameLevel) {
        this.body = body;
        Controller1.gameLevel = gameLevel;
    }

    static {
        try {
            jumpSound = new SoundClip("data/sounds/jump.wav");
            //System.out.println("Loading coin sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Allow player to move when W, A and D are used.
     *
     * @param e the Key Event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) { // Q = quit
            System.exit(0);
        } else if (code == KeyEvent.VK_W) { // W = jump
            Vec2 v = body.getLinearVelocity();

            // only jump if body is nearly stationary vertically
            if (Math.abs(v.y) < 0.01f) {
                body.jump(JUMPING_SPEED);
                body.removeAllImages();
                body.addImage(Player1.playerJump);
                jumpSound.play();
            }
        } else if (code == KeyEvent.VK_A) {
            body.startWalking(-WALKING_SPEED); // A = walk left
            body.removeAllImages();
            body.addImage(Player1.imageLeft);
            lastBodyPosLeft = true;
            lastBodyPosRight = false;

        } else if (code == KeyEvent.VK_9) {
            Player1.setCoinCount(10);

            //Switch to bullet type 1.
        } else if (code == KeyEvent.VK_1) {
            bulletType = 1;

            //Switch to bullet type 2.
        } else if (code == KeyEvent.VK_2) {
            bulletType = 2;

            //Super jump used for Dev use and Debugging
        } else if (code == KeyEvent.VK_5) {
            body.jump(60);

        } else if (code == KeyEvent.VK_ENTER) {
            if (Game.level <= 4) {
                System.exit(0);
            }

        } else if (code == KeyEvent.VK_D) {
            body.startWalking(WALKING_SPEED); // D = walk right
            body.removeAllImages();
            body.addImage(Player1.imageRight);
            lastBodyPosLeft = false;
            lastBodyPosRight = true;

        } else if (code == KeyEvent.VK_SPACE) {
            bulletNumber = bulletNumber + 1; // SPACE = shoot bullets
            //System.out.println(bulletNumber);

            if (Game.level == 0 || Game.level == 1) {
                //Do nothing for now.
            } else {
                //Create bullet and fire from Right or Left side of player depending on body position.
                Bullet bullet = new Bullet(gameLevel);
                if (lastBodyPosRight) {
                    //System.out.println("right");
                    bullet.setLinearVelocity(new Vec2(10.0f, 0f));
                    bullet.setPosition(new Vec2(GameLevel.p1.getPosition().x + 2.0f, GameLevel.p1.getPosition().y));
                    bullet.removeAllImages();
                    bullet.addImage(Bullet.bullet);
                }

                if (lastBodyPosLeft) {
                    //System.out.println("left");
                    //System.out.println(GameLevel.p1.getPosition().x);
                    bullet.setLinearVelocity(new Vec2(-10.0f, 0f));
                    bullet.setPosition(new Vec2(GameLevel.p1.getPosition().x - 2f, GameLevel.p1.getPosition().y));
                    bullet.removeAllImages();
                    bullet.addImage(Bullet.bullet);
                }
                bullet.addCollisionListener(new bulletCollision());

                if (bulletNumber == 5) {

                    for (int i = 0; i < gameLevel.getDynamicBodies().size(); i++) {
                        if (gameLevel.getDynamicBodies().get(i) instanceof Bullet) {
                            gameLevel.getDynamicBodies().get(i).destroy();
                        }
                    }
                    bulletNumber = 0;
                }
            }

        }

    }

    /**
     * Stop player moving when W, A and D are released
     *
     * @param e The Key Event.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            body.stopWalking();
            body.removeAllImages();
            body.addImage(Player1.imageIdleLeft);

        } else if (code == KeyEvent.VK_D) {
            body.stopWalking();
            body.removeAllImages();
            body.addImage(Player1.imageIdleRight);

        }
    }

    public void setBody(Walker body) {
        this.body = body;
    }

    public Walker getPlayer() {
        return body;
    }

    public Bullet getBullet() {
        return bulletBody;
    }

    public void setGameLevel(GameLevel gameLevel) {
        Controller1.gameLevel = gameLevel;
    }

    //When a mushroom is collected, increase jump speed by 20 but half health.
    public static void increaseJump() {
        JUMPING_SPEED = JUMPING_SPEED + 20;
        jumpIncreased = true;
        Player1.setHealthCount(Player1.getHealthCount() / 2);
    }

    public static void decreaseJump() {
        JUMPING_SPEED = JUMPING_SPEED - 20;
    }
}