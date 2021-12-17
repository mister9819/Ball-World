package edu.rice.comp504.model.strategy.collision;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.ICollisionStrategy;

/**
 * Default strategy does nothing.
 */
public class DefaultInteractionStrategy implements ICollisionStrategy {
    private static ICollisionStrategy ONLY;

    /**
     * Constructor.
     */
    private DefaultInteractionStrategy() {

    }

    /**
     * Only makes 1 default strategy.
     * @return The singleton default strategy
     */
    public static ICollisionStrategy make() {
        if (ONLY == null ) {
            ONLY = new DefaultInteractionStrategy();
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "default";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context, APaintObject context2) {

    }
}
