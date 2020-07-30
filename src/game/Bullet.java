package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.2
 * This class is Bullet.
 * This class sets the elements and variables for the Bullets fired in the game.
 */

public class Bullet extends DynamicBody {
    //Create the bullets hit boxes.
    private static final Shape shape = new BoxShape(0.2f, 0.2f);

    public static BodyImage bullet;

    //BodyImage for bullet type 1.
    public static final BodyImage bulletImage = new BodyImage("data/Bullet.png", 0.2f);

    //BodyImage for bullet type 2.
    public static final BodyImage billImage = new BodyImage("data/bulletbill.png", 0.8f);

    /**
     * Sets all the elements of the Bullets.
     *
     * @param world the game world.
     */
    public Bullet(World world) {
        super(world, shape);
        //Apply 0 gravity to the bullets.
        setGravityScale(0.0f);
        //Give the bullets linear velocity.
        setLinearVelocity(new Vec2(3.0f, 0f));
        //Add the bullets image to the shape.
        addImage(bulletImage);

        //Depending on which bullet type is selected, assign bullet images to bullets.
        if (Controller1.bulletType == 1) {
            bullet = bulletImage;
        } else if (Controller1.bulletType == 2) {
            bullet = billImage;
        }
    }
}
