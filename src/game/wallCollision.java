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
 *  @since 1.6
 * This class is wallCollision.
 * This class makes the enemy turn around when it reaches the wall.
 */

public class wallCollision implements CollisionListener {
    private Enemy1 enemy1;

    public wallCollision(Enemy1 enemy1) {
        this.enemy1 = enemy1;
    }

    static {
        try {
            SoundClip hitSound = new SoundClip("data/sounds/punch.wav");
            SoundClip deathSound = new SoundClip("data/sounds/death.wav");
            //System.out.println("Loading coin sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Turn the enemy around when it collides with a wall.
     *
     * @param e Collision Event.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == enemy1) {
            if (enemy1.getPosition().x > 0) {
                enemy1.startWalking(-2);
            } else {
                enemy1.startWalking(2);
            }

        }

    }

}
