package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.7
 * This class is DoorListener.
 * This class has all the parameters needed for the door collision.
 * Depending on whether the player has collected enough points.
 * The door will either let them progress to the next level or not.
 */

public class DoorListener implements CollisionListener {
    private Game game;

    public DoorListener(Game game) {
        this.game = game;
    }

    /**
     * Progresses the player to the next level when the player touches the door and has enough points.
     *
     * @param e Collision Event.
     */
    @Override
    public void collide(CollisionEvent e) {
        Player1 player = game.getPlayer();
        //The door allows the player to progress to the next level when their score is 10 or above.
        if (e.getOtherBody() == player && game.isCurrentLevelCompleted()) {
            Level0.introComplete = true;
            System.out.println("Going to next level...");
            game.goNextLevel();
        }
    }
}
