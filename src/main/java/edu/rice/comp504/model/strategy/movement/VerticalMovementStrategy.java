package edu.rice.comp504.model.strategy.movement;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

/**
 * Vertical strategy moves the object only in the y direction.
 */
public class VerticalMovementStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;

    /**
     * Constructor.
     */
    private VerticalMovementStrategy() {

    }

    /**
     * Only makes 1 vertical strategy.
     * @return The vertical strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null ) {
            ONLY = new VerticalMovementStrategy();
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "vertical";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context) {
        Point vel = context.getVelocity();
        Point nloc = context.getLocation();
        nloc.y += vel.y;
        context.setLocation(nloc);
    }
}
