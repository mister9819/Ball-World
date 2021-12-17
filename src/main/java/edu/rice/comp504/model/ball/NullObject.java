package edu.rice.comp504.model.ball;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.strategy.collision.DefaultInteractionStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.common.NullStrategy;

import java.awt.*;
import java.beans.PropertyChangeEvent;

/**
 * NullObject represents an initial or unexpected paint object type.
 */
public class NullObject extends APaintObject {
    private static NullObject ONLY;

    /**
     * Constructor.
     */
    public NullObject() {
        super(new Point(0,0), new Point(0,0), 0, 0, "white", "null",
                BallWorldStore.getOnlyMSFac().make("null"), BallWorldStore.getOnlyCSFac().make("null"));
    }

    /**
     * Make a null object.  There is only one (singleton).
     * @return A null object
     */
    public static NullObject make() {
        if (ONLY == null) {
            ONLY = new NullObject();
        }
        return ONLY;
    }

    /**
     * Detect collision.
     * @return false - cannot detect collision with null object
     */
    @Override
    public boolean detectCollision() {
        return false;
    }
}
