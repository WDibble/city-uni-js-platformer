package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.1
 * This class is Coin.
 * This class sets the elements and variables for the coins in the game.
 * When a coin is collected, a players score goes up by 1.
 */

public class Coin extends StaticBody {

    //Generate coin with a circle shape.
    public static final Shape shape = new CircleShape(0.1f);

    private static SoundClip coinSound;

    //Load sound file for coin into a variable.
    static {
        try {
            coinSound = new SoundClip("data/sounds/coin.wav");
            //System.out.println("Loading coin sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * When a coin is collected, play the collection sound and destroy the coin.
     */
    @Override
    public void destroy() {
        coinSound.play();
        super.destroy();
    }

    /**
     * Sets the coins image.
     *
     * @param world the world.
     */
    public Coin(World world) {
        super(world, shape);
        addImage(new BodyImage("data/coin.gif", 1f));
    }
}
