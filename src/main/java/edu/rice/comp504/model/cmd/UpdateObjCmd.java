package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.ball.Ball;

import java.beans.PropertyChangeListener;

/**
 * Command class to update using strategy of object.
 */
public class UpdateObjCmd implements IObjCmd {

    PropertyChangeListener[] objects;

    public UpdateObjCmd(PropertyChangeListener[] objects) {
        this.objects = objects;
    }

    /**
     * Execute the update ball command.
     * @param context The receiver on which the command is executed.
     */
    @Override
    public void execute(APaintObject context) {
        if (context != null) {

            for (PropertyChangeListener ball : this.objects) {
                if (ball instanceof Ball && ball != context) {
                    if (context.detectObjectCollision((Ball) ball)) {
                        context.getInteractionStrategy().updateState(context, (Ball) ball);
                        ((Ball) ball).getInteractionStrategy().updateState((Ball) ball, context);
                    }
                }
            }

            context.getMovementStrategy().updateState(context);
            context.detectCollision();
        }
    }
}
