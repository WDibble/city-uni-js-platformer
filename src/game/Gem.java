package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.6
 * This class is Gem.
 * This class sets the elements and variables for the gems in the game.
 * When a gem is collected, a players score goes up by 3.
 */

public class Gem extends StaticBody {

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
     * Destroy the Coin.
     */
    @Override
    public void destroy() {
        coinSound.play();
        super.destroy();
    }

    /**
     * Create the Gem.
     *
     * @param world in the game world.
     */
    public Gem(World world) {
        super(world, shape);
        addImage(new BodyImage("data/gem.gif", 1f));
    }
}
