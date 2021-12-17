package edu.rice.comp504.model.strategy.common;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.ICollisionStrategy;
import edu.rice.comp504.model.strategy.IStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.movement.CompositeMovementStrategy;

/**
 * Blink strategy makes the object blink.
 */
public class BlinkingStrategy implements IUpdateStrategy, ICollisionStrategy {
    boolean blink;
    String prevColor;
    IUpdateStrategy child;

    /**
     * Constructor.
     */
    private BlinkingStrategy() {
        child = CompositeMovementStrategy.make();
        blink = true;
    }

    /**
     * Makes blinking strategy.
     * @return The blinking strategy
     */
    public static IStrategy make() {
        return new BlinkingStrategy();
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "blinking";
    }

    /**
     * Update the object state on collision in the ball world.
     * @param context The object to update
     */
    @Override
    public void updateState(APaintObject context, APaintObject context2) {
        update(context);
    }

    /**
     * Update the object state in the ball world.
     * @param context The object to update
     */
    @Override
    public void updateState(APaintObject context) {
        update(context);
        child.updateState(context);
    }

    /**
     * Helper update function.
     * @param context The object to update
     */
    private void update(APaintObject context) {
        if (blink) {
            prevColor = context.getColor();
            context.setColor("white");
        } else {
            context.setColor(prevColor);
        }
        blink = !blink;
    }
}
