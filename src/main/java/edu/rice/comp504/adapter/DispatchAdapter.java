package edu.rice.comp504.adapter;

import edu.rice.comp504.model.BallWorldStore;

import java.beans.PropertyChangeListener;

/**
 * This adapter interfaces with the view (paint objects) and the controller.
 */
public class DispatchAdapter {

    BallWorldStore ballWorldStore;

    /**
     * Constructor call.
     */
    public DispatchAdapter() {
        ballWorldStore = new BallWorldStore();
    }

    /**
     * Set the canvas dimensions.
     * @param dims The canvas width (x) and height (y).
     */
    public void setCanvasDims(String dims) {
        BallWorldStore.setCanvasDims(dims);
    }

    /**
     * Update the ball world.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] updateBallWorld() {
        return ballWorldStore.updateBallWorld();
    }

    /**
     * Load a ball into the paint world.
     * @param strat  The REST request strategy.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] loadBall(String strat, String type) {
        ballWorldStore.loadObject(strat, type);
        return updateBallWorld();
    }


    /**
     * Switch the strategy for switcher balls
     * @param strat  The REST request strategy.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] switchStrategy(String strat) {
        ballWorldStore.switchStrategy(strat);
        return null;
    }

    /**
     * Remove all balls from listening for property change events for a particular property.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] removeAll() {
        ballWorldStore.removeBallsFromStore();
        return ballWorldStore.updateBallWorld();
    }

    /**
     * Remove few balls from listening for property change events for a particular property.
     */
    public void removeFew(String strat) {
        ballWorldStore.removeFewBallsFromStore(strat);
    }

}
