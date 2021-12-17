package edu.rice.comp504.model.strategy.collision;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.ICollisionStrategy;

/**
 * Color infector strategy changes the color of the other object to that of current object.
 */
public class ColorInfectorStrategy implements ICollisionStrategy {
    private static ICollisionStrategy ONLY;

    /**
     * Constructor.
     */
    private ColorInfectorStrategy() {

    }

    /**
     * Only makes 1 colorchange strategy.
     * @return The singleton colorchange strategy
     */
    public static ICollisionStrategy make() {
        if (ONLY == null ) {
            ONLY = new ColorInfectorStrategy();
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "colorinfector";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context, APaintObject context2) {
        context2.setColor(context.getColor());
    }
}
