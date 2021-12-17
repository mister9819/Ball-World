package edu.rice.comp504.model.strategy.movement;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

/**
 * Zigzag strategy moves the object in the x and y direction alternatively.
 */
public class ZigzagMovementStrategy implements IUpdateStrategy {
    boolean zig;

    /**
     * Constructor.
     */
    private ZigzagMovementStrategy() {
        zig = false;
    }

    /**
     * Only makes 1 zigzag strategy.
     * @return The zigzag strategy
     */
    public static IUpdateStrategy make() {
        return new ZigzagMovementStrategy();
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "zigzag";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context) {
        Point vel = context.getVelocity();
        Point nloc = context.getLocation();

        if (zig) {
            nloc.y += vel.y;
        } else {
            nloc.x += vel.x;
        }
        zig = !zig;
        context.setLocation(nloc);
    }
}
