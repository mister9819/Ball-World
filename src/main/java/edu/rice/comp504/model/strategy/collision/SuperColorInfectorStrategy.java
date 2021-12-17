package edu.rice.comp504.model.strategy.collision;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.ICollisionStrategy;

/**
 * Super color infector strategy infects the other objects to have the same color infector strategy.
 */
public class SuperColorInfectorStrategy implements ICollisionStrategy {
    private static ICollisionStrategy ONLY;

    /**
     * Constructor.
     */
    private SuperColorInfectorStrategy() {

    }

    /**
     * Only makes 1 super color infector strategy.
     * @return The singleton super color infector strategy
     */
    public static ICollisionStrategy make() {
        if (ONLY == null ) {
            ONLY = new SuperColorInfectorStrategy();
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "supercolorinfector";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context, APaintObject context2) {
        context2.setColor(context.getColor());
        context2.setInteractionStrategy(SuperColorInfectorStrategy.make());
    }
}
