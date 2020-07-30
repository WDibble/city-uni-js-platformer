package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.1
 * This class is Pickup.
 * This class has all the parameters needed for the coin collision.
 * When a coin is collected, the players score goes up by 1 point, the coin is then destroyed.
 */

public class Pickup implements CollisionListener {
    private Player1 p1;

    public Pickup(Player1 p1) {
        this.p1 = p1;
    }

    /**
     * Pickup coin on collision.
     *
     * @param e Collision Event.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == p1) {
            p1.incrementCoinCount();
            e.getReportingBody().destroy();
        }
    }

}
