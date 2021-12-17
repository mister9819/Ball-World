package edu.rice.comp504.model.strategy.movement;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

/**
 * Rotate strategy moves the object in rotation.
 */
public class RotateMovementStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;

    /**
     * Constructor.
     */
    private RotateMovementStrategy() {

    }

    /**
     * Only makes 1 rotate strategy.
     * @return The rotate strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null ) {
            ONLY = new RotateMovementStrategy();
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "rotate";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context) {
        Point vel = context.getVelocity();
        Point nloc = context.getLocation();
        double angle = Math.PI / 8;

        Point nvel = new Point((int) Math.round(vel.x * Math.cos(angle) - vel.y * Math.sin(angle)),
                (int) Math.round(vel.y * Math.cos(angle) + vel.x * Math.sin(angle)));
        nloc.x += nvel.x;
        nloc.y += nvel.y;

        context.setLocation(nloc);
        context.setVelocity(nvel);
    }
}
