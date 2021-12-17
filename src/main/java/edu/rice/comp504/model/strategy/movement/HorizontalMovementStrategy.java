package edu.rice.comp504.model.strategy.movement;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

/**
 * Horizontal strategy moves the object only in the x direction.
 */
public class HorizontalMovementStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;

    /**
     * Constructor.
     */
    private HorizontalMovementStrategy() {

    }

    /**
     * Only makes 1 horizontal strategy.
     * @return The horizontal strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null ) {
            ONLY = new HorizontalMovementStrategy();
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "horizontal";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context) {
        Point vel = context.getVelocity();
        Point nloc = context.getLocation();
        nloc.x += vel.x;
        context.setLocation(nloc);
    }
}
