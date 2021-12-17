package edu.rice.comp504.model.cmd;

import java.beans.PropertyChangeListener;

/**
 * A factory that makes commands.
 */
public interface ICmdFac {

    /**
     * Make the command.
     * @param type of command
     * @param t execute commands on these
     * @return The command
     */
    IObjCmd make(String type, PropertyChangeListener[] t);
}
