package game;

import city.cs.engine.*;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.12
 * This class is Spikes.
 * This class has all the variables needed to create spikes that deal damage to the player.
 */

public class Spikes extends StaticBody {

    //Generate coin with a circle shape
    public static final Shape shape = new BoxShape(1, 0.25f);

    /**
     * Generate a spike.
     *
     * @param world in the game world.
     */
    public Spikes(World world) {
        super(world, shape);
        addImage(new BodyImage("data/spikes.png", 1f));
    }
}
