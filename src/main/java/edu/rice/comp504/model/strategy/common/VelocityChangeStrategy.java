package edu.rice.comp504.model.strategy.common;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.ICollisionStrategy;
import edu.rice.comp504.model.strategy.IStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.movement.CompositeMovementStrategy;

import java.awt.*;

/**
 * Velocity strategy changes the velocity of the object.
 */
public class VelocityChangeStrategy implements IUpdateStrategy, ICollisionStrategy {
    int faster;
    IUpdateStrategy child;

    /**
     * Constructor.
     */
    private VelocityChangeStrategy() {
        child = CompositeMovementStrategy.make();
        faster = 0;
    }

    /**
     * Make velocity strategy.
     * @return The velocity strategy
     */
    public static IStrategy make() {
        return new VelocityChangeStrategy();
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "velchange";
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
        int changeVelAmount = 5;
        Point vel = context.getVelocity();

        if (faster % 8 < 4) {
            if (vel.x > 0) {
                vel.x += changeVelAmount * (faster + 1);
            } else {
                vel.x -= changeVelAmount * (faster + 1);
            }
            if (vel.y > 0) {
                vel.y += changeVelAmount * (faster + 1);
            } else {
                vel.y -= changeVelAmount * (faster + 1);
            }
        } else {
            if (vel.x > 0) {
                vel.x -= changeVelAmount * (faster - 3);
            } else {
                vel.x += changeVelAmount * (faster - 3);
            }
            if (vel.y > 0) {
                vel.y -= changeVelAmount * (faster - 3);
            } else {
                vel.y += changeVelAmount * (faster - 3);
            }
        }
        context.setVelocity(vel);
        faster++;
        if (faster == 8) {
            faster = 0;
        }
    }
}
