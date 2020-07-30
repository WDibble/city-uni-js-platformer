package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.2
 * This class is FireballCollision.
 * This class has all the parameters needed for the fireball collision.
 * So when the player is hit by a fireball, the player either dies or takes damage.
 */

public class FireballCollision implements CollisionListener {

    public FireballCollision(Player1 p1) {
    }

    private static SoundClip hitSound;
    private static SoundClip deathSound;

    private GameLevel world;

    static {
        try {
            hitSound = new SoundClip("data/sounds/punch.wav");
            deathSound = new SoundClip("data/sounds/death.wav");
            //System.out.println("Loading coin sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Creates the Fireballs Colllision.
     *
     * @param e the Collision Event.
     */
    @Override
    public void collide(CollisionEvent e) {
        var otherBody = e.getOtherBody();

        if (otherBody instanceof Player1) {
            Player1 player = (Player1) otherBody;
            if (Player1.getHealthCount() < 5) {
                Player1.setHealthCount(0);
                deathSound.play();
                player.destroy();
                Game.level = 6;
            } else {
                player.incrementHealthCount();
                player.startWalking(10);
                player.jump(40);
                player.startWalking(0);
                hitSound.play();
            }

        }
        e.getReportingBody().destroy();

    }

}
