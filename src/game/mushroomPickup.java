package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.7
 * This class is mushroomPickup.
 * This class has all the parameters needed for the mushroom collision.
 * This mushroom can be collected to double the players jump height.
 * It, however, halves the players health as compensation.
 */

public class mushroomPickup implements CollisionListener {
    private Player1 p1;

    public mushroomPickup(Player1 p1) {
        this.p1 = p1;
    }

    /**
     * Collect Mushroom on collision.
     *
     * @param e Collision Event.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == p1) {
            Controller1.increaseJump();
            e.getReportingBody().destroy();
        }
    }

}
