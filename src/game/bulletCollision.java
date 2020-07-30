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
 * This class is bulletCollision.
 * This class sets all the parameters needed for the bullet collision.
 * So depending on which bullet is fired, each enemy reacts differently.
 */

public class bulletCollision implements CollisionListener {

    private static SoundClip hitSound;
    private static SoundClip deathSound;

    //Import sound files for the bullets and assign the to variables.
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
     * Allows the bullet to collide with other objects.
     * Zombies are only affected by bullet type 1.
     * Robots are only affected by bullet type 2.
     *
     * @param e collision.
     */
    @Override
    public void collide(CollisionEvent e) {
        //If a bullet collides with an enemy at low health, play the death sound and destroy the enemy.
        if (Controller1.bulletType == 1) {
            var otherBody = e.getOtherBody();
            if (otherBody instanceof Enemy1) {
                Enemy1 enemy = (Enemy1) otherBody;

                if (enemy.getHealthCount() < 5) {
                    enemy.healthCount = 0;
                    deathSound.play();
                    e.getOtherBody().destroy();
                }

                //If a bullet collides with an enemy at high health, lower the enemies health by 10.
                else {
                    enemy.healthCount = enemy.healthCount - 10;
                    enemy.jump(10);
                    hitSound.play();
                }
            }
            e.getReportingBody().destroy();
        } else if (Controller1.bulletType == 2) {
            var otherBody = e.getOtherBody();
            //If the body the type 2 bullet is colliding with is an instance of Enemy2,
            if (otherBody instanceof Enemy2) {
                Enemy2 enemy = (Enemy2) otherBody;

                if (enemy.getHealthCount() < 5) {
                    enemy.healthCount = 0;
                    deathSound.play();
                    e.getOtherBody().destroy();
                }

                //If a bullet collides with an enemy at high health, lower the enemies health by 10.
                else {
                    enemy.healthCount = enemy.healthCount - 10;
                    enemy.jump(10);
                    hitSound.play();
                }
            }
            //Whenever a bullet collides ith another object, destroy it.
            e.getReportingBody().destroy();
        }


    }

}
