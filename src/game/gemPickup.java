package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.6
 * This class is gemPickup.
 * This class has all the parameters needed for the gem collision.
 * When a gem is collected, the gem is them destroyed.
 */

public class gemPickup implements CollisionListener {
    private Player1 p1;

    public gemPickup(Player1 p1) {
        this.p1 = p1;
    }

    /**
     * Collect the gem on collision.
     *
     * @param e the Collision Event.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == p1) {
            p1.incrementGemCount();
            e.getReportingBody().destroy();
        }
    }

}
