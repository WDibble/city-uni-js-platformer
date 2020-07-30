package game;

import city.cs.engine.Body;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.WorldView;
import org.jbox2d.common.Vec2;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.0
 * This class is Tracker.
 * This class allows the camera to follow the players movement in the game.
 */

public class Tracker implements StepListener {

    private WorldView view;

    private Body body;

    /**
     * Tracker so camera follows player movement.
     *
     * @param view The Players View.
     * @param body The Player.
     */
    public Tracker(WorldView view, Body body) {
        this.view = view;
        this.body = body;
    }

    @Override
    public void preStep(StepEvent e) {
    }

    @Override
    public void postStep(StepEvent e) {
        view.setCentre(new Vec2(body.getPosition()));
    }

}
