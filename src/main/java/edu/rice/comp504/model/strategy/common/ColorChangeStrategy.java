package edu.rice.comp504.model.strategy.common;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.ICollisionStrategy;
import edu.rice.comp504.model.strategy.IStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.movement.CompositeMovementStrategy;

/**
 * Color change strategy makes the object change color.
 */
public class ColorChangeStrategy implements IUpdateStrategy, ICollisionStrategy {
    private static IStrategy ONLY;
    IUpdateStrategy child;

    /**
     * Constructor.
     */
    private ColorChangeStrategy() {
        child = CompositeMovementStrategy.make();
    }

    /**
     * Only makes 1 color change strategy.
     * @return The color change strategy
     */
    public static IStrategy make() {
        if (ONLY == null ) {
            ONLY = new ColorChangeStrategy();
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "colorchange";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context) {
        context.setColor(BallWorldStore.randomColor(context.getColor()));
        child.updateState(context);
    }

    @Override
    public void updateState(APaintObject context, APaintObject context2) {
        context.setColor(BallWorldStore.randomColor(context.getColor()));
    }
}
