package edu.rice.comp504.model.strategy.movement;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.common.VelocityChangeStrategy;

/**
 * The bounce movement + velocity change strategy potentially has multiple strategies as children.
 */
public class BounceMovementVelocityChangeStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;
    private IUpdateStrategy[] children;

    /**
     * Constructor.
     */
    private BounceMovementVelocityChangeStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }

    /**
     * Get the singleton Bounce Movement Velocity Change strategy.
     * @return The singleton Bounce Movement Velocity Change strategy
     */
    public static IUpdateStrategy make() {
        IUpdateStrategy[] children = new IUpdateStrategy[2];
        children[0] = BounceMovementStrategy.make();
        children[1] = (IUpdateStrategy) VelocityChangeStrategy.make();
        return new BounceMovementVelocityChangeStrategy(children);
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "bouncevel";
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
