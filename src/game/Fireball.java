package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.12
 * This class is FileSorter.
 * This class reads all the information from a .txt file.
 * Organises the information through a bubble sort.
 * And writes it all back to the file.
 */

public class Fireball extends DynamicBody {
    private static final Shape shape = new BoxShape(0.3f, 0.3f);

    public static final BodyImage fireballImage = new BodyImage("data/fireball.gif", 1f);

    /**
     * Creates the fireball.
     *
     * @param world the game world.
     */
    public Fireball(World world) {
        super(world, shape);
        setGravityScale(0.0f);
        setLinearVelocity(new Vec2(3.0f, 0f));
        addImage(fireballImage);
    }
}
