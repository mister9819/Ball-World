package edu.rice.comp504.model.strategy.movement;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.common.SizeChangeStrategy;

/**
 * The Zigzag Movement Color Change strategy potentially has multiple strategies as children.
 */
public class ZigzagMovementColorChangeStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;
    private IUpdateStrategy[] children;

    /**
     * Constructor.
     */
    private ZigzagMovementColorChangeStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }

    /**
     * Get the singleton Zigzag Movement Color Change strategy.
     * @return The singleton Zigzag Movement Color Change strategy
     */
    public static IUpdateStrategy make() {
        IUpdateStrategy[] children = new IUpdateStrategy[2];
        children[0] = ZigzagMovementStrategy.make();
        children[1] = (IUpdateStrategy) SizeChangeStrategy.make();
        return new ZigzagMovementColorChangeStrategy(children);
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "zigzagcolor";
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
