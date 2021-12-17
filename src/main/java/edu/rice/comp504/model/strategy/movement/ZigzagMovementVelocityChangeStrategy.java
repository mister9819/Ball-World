package edu.rice.comp504.model.strategy.movement;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.common.VelocityChangeStrategy;

/**
 * The Zigzag Movement Velocity Change strategy potentially has multiple strategies as children.
 */
public class ZigzagMovementVelocityChangeStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;
    private IUpdateStrategy[] children;

    /**
     * Constructor.
     */
    private ZigzagMovementVelocityChangeStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }

    /**
     * Get the singleton Zigzag Movement Velocity Change strategy.
     * @return The singleton Zigzag Movement Velocity Change strategy
     */
    public static IUpdateStrategy make() {
        IUpdateStrategy[] children = new IUpdateStrategy[2];
        children[0] = ZigzagMovementStrategy.make();
        children[1] = (IUpdateStrategy) VelocityChangeStrategy.make();
        return new ZigzagMovementVelocityChangeStrategy(children);
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "zigzagvel";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        children[0].updateState(context);
        ((VelocityChangeStrategy) children[1]).updateState(context, context);
    }
}
