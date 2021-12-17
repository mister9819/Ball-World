package edu.rice.comp504.model.strategy.collision;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.ball.Ball;
import edu.rice.comp504.model.strategy.ICollisionStrategy;
import edu.rice.comp504.model.strategy.IStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.common.NullStrategy;

/**
 * Destroyer strategy destorys the other object.
 */
public class DestroyerStrategy implements ICollisionStrategy {
    private static IStrategy ONLY;

    /**
     * Constructor.
     */
    private DestroyerStrategy() {

    }

    /**
     * Only makes 1 destroyer strategy.
     * @return The singleton destroyer strategy
     */
    public static IStrategy make() {
        if (ONLY == null ) {
            ONLY = new DestroyerStrategy();
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "destoyer";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context, APaintObject context2) {
        if (context2 instanceof Ball) {
            context2.setMovementStrategy((IUpdateStrategy) NullStrategy.make());
        }
    }
}
