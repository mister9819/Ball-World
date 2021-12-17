package edu.rice.comp504.model.strategy.collision;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.ICollisionStrategy;
import edu.rice.comp504.model.strategy.IStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.common.NullStrategy;

/**
 * Self-destruct strategy makes the current object destroyed.
 */
public class SelfDestructStrategy implements ICollisionStrategy {
    private static IStrategy ONLY;

    /**
     * Constructor.
     */
    private SelfDestructStrategy() {

    }

    /**
     * Only makes 1 self destruct strategy.
     * @return The singleton self destruct strategy
     */
    public static IStrategy make() {
        if (ONLY == null ) {
            ONLY = new SelfDestructStrategy();
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "selfdestruct";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context, APaintObject context2) {
        context.setMovementStrategy((IUpdateStrategy) NullStrategy.make());
    }
}
