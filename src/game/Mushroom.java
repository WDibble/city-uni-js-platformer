package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.7
 * This class is Mushroom.
 * This class all the variables and objects needed to create a Mushroom.
 * This mushroom can be collected to double the players jump height.
 * It, however, halves the players health as compensation.
 */

public class Mushroom extends StaticBody {

    //Generate coin with a circle shape
    public static final Shape shape = new CircleShape(0.1f);

    private static SoundClip coinSound;

    static {
        try {
            coinSound = new SoundClip("data/sounds/coin.wav");
            //System.out.println("Loading coin sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Upon collision, destroy the Mushroom.
     */
    @Override
    public void destroy() {
        coinSound.play();
        super.destroy();
    }

    /**
     * Create the Mushroom.
     *
     * @param world in the world.
     */
    public Mushroom(World world) {
        super(world, shape);
        addImage(new BodyImage("data/mushroom.png", 1f));
    }
}
