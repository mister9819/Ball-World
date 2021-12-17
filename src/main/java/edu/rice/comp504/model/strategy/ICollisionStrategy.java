package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.ball.APaintObject;

/**
 * An interface for ball strategies that determine the object interaction.
 */
public interface ICollisionStrategy extends IStrategy {

    /**
     * Update the state of the ball.
     * @param context The Object.
     * @param context2 The Object.
     */
    void updateState(APaintObject context, APaintObject context2);
}
