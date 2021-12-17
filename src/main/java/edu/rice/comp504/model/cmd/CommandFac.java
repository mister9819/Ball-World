package edu.rice.comp504.model.cmd;

import java.beans.PropertyChangeListener;

/**
 * Factory to make commands.
 */
public class CommandFac implements ICmdFac {

    private static CommandFac ONLY;

    public CommandFac() {
    }

    @Override
    public IObjCmd make(String type, PropertyChangeListener[] t) {
        switch (type) {
            case "switch":
                return new SwitchObjCmd(t);
            case "update":
                return new UpdateObjCmd(t);
            default:
                return null;
        }
    }
}
