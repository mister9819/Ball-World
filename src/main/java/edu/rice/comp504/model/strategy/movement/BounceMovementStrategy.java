package edu.rice.comp504.model.strategy.movement;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

/**
 * Bounce strategy moves the object up and down.
 */
public class BounceMovementStrategy implements IUpdateStrategy {
    boolean bounce;

    /**
     * Constructor.
     */
    private BounceMovementStrategy() {
        bounce = false;
    }

    /**
     * Only makes 1 bounce strategy.
     * @return The bounce strategy
     */
    public static IUpdateStrategy make() {
        return new BounceMovementStrategy();
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "bounce";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context) {
        Point vel = context.getVelocity();
        Point nloc = context.getLocation();

        if (bounce) {
            nloc.y += vel.y * -2;
        } else {
            nloc.y += vel.y * 2;
        }
        bounce = !bounce;
        context.setLocation(nloc);
    }
}
