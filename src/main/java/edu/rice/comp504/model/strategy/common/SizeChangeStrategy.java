package edu.rice.comp504.model.strategy.common;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.ball.Ball;
import edu.rice.comp504.model.strategy.ICollisionStrategy;
import edu.rice.comp504.model.strategy.IStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.movement.CompositeMovementStrategy;

/**
 * Size change strategy makes the object alternatively larger and smaller.
 */
public class SizeChangeStrategy implements IUpdateStrategy, ICollisionStrategy {
    boolean bigger;
    IUpdateStrategy child;

    /**
     * Constructor.
     */
    private SizeChangeStrategy() {
        child = CompositeMovementStrategy.make();
        bigger = true;
    }

    /**
     * Make size change strategy.
     * @return The size change strategy
     */
    public static IStrategy make() {
        return new SizeChangeStrategy();
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "sizechange";
    }

    @Override
    public void updateState(APaintObject context, APaintObject context2) {
        update(context);
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context) {
        update(context);
        child.updateState(context);
    }

    private void update(APaintObject context) {
        int height = context.getHeight();
        int width = context.getWidth();
        int changeSizeAmount;
        if (context instanceof Ball) {
            changeSizeAmount = 10;
        } else {
            changeSizeAmount = 20;
        }


        //increase the size of the paint
        if (bigger) {
            context.setHeight(height + changeSizeAmount);
            context.setWidth(width + changeSizeAmount);
        } else {
            context.setHeight(height - changeSizeAmount);
            context.setWidth(width - changeSizeAmount);
        }

        bigger = !bigger;
    }
}
