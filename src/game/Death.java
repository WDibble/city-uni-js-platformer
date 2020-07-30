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
 *  @since 1.4
 * This class is Death.
 * This class sets the parameters needed to decide when the player will loose health or the player will die.
 */

public class Death implements CollisionListener {
    private Player1 p1;

    public Death(Player1 p1) {
        this.p1 = p1;
    }

    private static SoundClip hitSound;
    private static SoundClip deathSound;

    static {
        try {
            hitSound = new SoundClip("data/sounds/punch.wav");
            deathSound = new SoundClip("data/sounds/death.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Allow death to be linked to the player and called when damage is done to the player.
     *
     * @param e Collision Event.
     */
    @Override
    public void collide(CollisionEvent e) {
        //If the players health drops below 5, kill the player.
        if (e.getOtherBody() == p1) {
            if (Player1.getHealthCount() < 5) {
                Player1.setHealthCount(0);
                deathSound.play();
                p1.destroy();
                Game.level = 6;
            } else {
                //If the players health is still above 5, decrease the players health by 10 and make they fly into the air.
                p1.incrementHealthCount();
                p1.startWalking(10);
                p1.jump(40);
                p1.startWalking(0);
                hitSound.play();
            }

        }

    }

}
