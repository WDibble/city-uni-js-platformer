package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.7
 * This class is Door.
 * This class sets the elements and variables to initialise the door that is used to progress through the levels.
 */

public class Door extends StaticBody {

    public static final BodyImage portalGreen =
            new BodyImage("data/portal.gif", 2.8f);

    /**
     * The door allows the player to progress to the next level when their score is 10 or above.
     *
     * @param world the world.
     */
    public Door(World world) {
        super(world, new BoxShape(0.8f, 0.8f));
        addImage(portalGreen);
    }

}
