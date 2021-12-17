package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.SwitchStrategy;

import java.beans.PropertyChangeListener;

/**
 * Command class to switch strategy of object.
 */
public class SwitchObjCmd implements IObjCmd {

    PropertyChangeListener[] switchs;

    public SwitchObjCmd(PropertyChangeListener[] switchs) {
        this.switchs = switchs;
    }

    /**
     * Execute the switch ball command.
     * @param context The receiver on which the command is executed.
     */
    @Override
    public void execute(APaintObject context) {
        if (context != null) {
            String currentStrategy = context.getMovementStrategy().getName();
            SwitchStrategy s = ((SwitchStrategy) switchs[0]);
            if (currentStrategy.equals(s.getFrom())) {
                context.setMovementStrategy(
                        BallWorldStore.getOnlyMSFac().make(s.getTo())
                );
            }
        }
    }
}
