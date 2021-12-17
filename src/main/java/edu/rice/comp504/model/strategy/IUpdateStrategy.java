package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.ball.Ball;

/**
 * An interface for ball strategies that determine the object behavior.
 */
public interface IUpdateStrategy extends IStrategy {

    /**
     * Update the state of the object.
     * @param context The object.
     */
    void updateState(APaintObject context);
}
