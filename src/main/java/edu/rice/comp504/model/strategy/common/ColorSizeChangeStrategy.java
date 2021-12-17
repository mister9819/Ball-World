package edu.rice.comp504.model.strategy.common;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.ICollisionStrategy;
import edu.rice.comp504.model.strategy.IStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.movement.CompositeMovementStrategy;

/**
 * The color + size change strategy potentially has multiple strategies as children.
 */
public class ColorSizeChangeStrategy implements IUpdateStrategy, ICollisionStrategy {
    private IStrategy[] children;

    /**
     * Constructor.
     */
    private ColorSizeChangeStrategy(IStrategy[] children) {
        this.children = children;
    }

    /**
     * Get color size change strategy.
     * @return The color size change strategy
     */
    public static IStrategy make() {
        IStrategy[] children = new IStrategy[3];
        children[0] = ColorChangeStrategy.make();
        children[1] = SizeChangeStrategy.make();
        children[2] = CompositeMovementStrategy.make();
        return new ColorSizeChangeStrategy(children);
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "colorsizechange";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        for (int i = 0; i < 2; i++) {
            ((ICollisionStrategy) children[i]).updateState(context, context);
        }
        ((IUpdateStrategy) children[2]).updateState(context);
    }

    @Override
    public void updateState(APaintObject context, APaintObject context2) {
        for (int i = 0; i < 2; i++) {
            ((ICollisionStrategy) children[i]).updateState(context, context);
        }
    }
}
