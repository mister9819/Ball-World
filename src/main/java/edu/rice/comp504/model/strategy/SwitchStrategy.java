package edu.rice.comp504.model.strategy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The class used to switch strategy in objects.
 */
public class SwitchStrategy implements PropertyChangeListener {
    String from;
    String to;
    static SwitchStrategy ONLY;

    private SwitchStrategy(String from, String to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Make a switch strategy.  There is only one (singleton).
     * @return The singleton switch strategy
     */
    public static SwitchStrategy make(String from, String to) {
        if (ONLY == null) {
            ONLY = new SwitchStrategy(from, to);
        }
        return ONLY;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
