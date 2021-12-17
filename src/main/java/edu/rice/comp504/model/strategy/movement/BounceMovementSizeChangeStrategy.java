package edu.rice.comp504.model.strategy.movement;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.common.SizeChangeStrategy;

/**
 * The bounce movement + size change strategy potentially has multiple strategies as children.
 */
public class BounceMovementSizeChangeStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;
    private IUpdateStrategy[] children;

    /**
     * Constructor.
     */
    private BounceMovementSizeChangeStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }

    /**
     * Get the singleton Bounce Movement Size Change strategy.
     * @return The singleton Bounce Movement Size Change strategy
     */
    public static IUpdateStrategy make() {
        IUpdateStrategy[] children = new IUpdateStrategy[2];
        children[0] = BounceMovementStrategy.make();
        children[1] = (IUpdateStrategy) SizeChangeStrategy.make();
        return new BounceMovementSizeChangeStrategy(children);
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "bouncesize";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        children[0].updateState(context);
        ((SizeChangeStrategy) children[1]).updateState(context, context);
    }
}
