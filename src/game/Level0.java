package game;

import org.jbox2d.common.Vec2;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.5
 * This class is Level0.
 * This class has all the parameters, variables and objects needed to populate Level 0.
 * This level sets up the rest of the game in terms of either showing how the player can play the game,
 * or by taking an already saved game and loading it for the player.
 */

public class Level0 extends GameLevel {

    private static final int NUM_SCORE = 0;

    public static boolean introComplete = false;

    public static boolean doorReady = (Player1.getCoinCount() > NUM_SCORE);


    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, -9);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(0, -5);
    }

    @Override
    public boolean isCompleted() {
        return Player1.getCoinCount() >= NUM_SCORE;
    }

}
