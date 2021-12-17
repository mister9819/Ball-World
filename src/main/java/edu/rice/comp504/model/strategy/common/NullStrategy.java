package edu.rice.comp504.model.strategy.common;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.ICollisionStrategy;
import edu.rice.comp504.model.strategy.IStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

/**
 * The null strategy changing the color of a paint object to white.
 */
public class NullStrategy implements IUpdateStrategy, ICollisionStrategy {
    private static IStrategy ONLY;

    /**
     * Constructor.
     */
    private NullStrategy() {

    }

    /**
     * Make a Null strategy.  There is only one (singleton).
     * @return The singleton Null strategy
     */
    public static IStrategy make() {
        if (ONLY == null) {
            ONLY = new NullStrategy();
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "null";
    }

    /**
     * Update the state in the world.
     * @param context The to update
     */
    @Override
    public void updateState(APaintObject context) {
        context.setLocation(new Point(900, 900));
        context.setWidth(0);
        context.setHeight(0);
    }

    @Override
    public void updateState(APaintObject context, APaintObject context2) {
        context.setLocation(new Point(900, 900));
        context.setWidth(0);
        context.setHeight(0);
    }
}
