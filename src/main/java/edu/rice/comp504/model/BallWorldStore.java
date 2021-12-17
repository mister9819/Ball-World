package edu.rice.comp504.model;

import edu.rice.comp504.model.ball.*;
import edu.rice.comp504.model.cmd.CommandFac;
import edu.rice.comp504.model.cmd.ICmdFac;
import edu.rice.comp504.model.strategy.CollisionStrategyFac;
import edu.rice.comp504.model.strategy.SwitchStrategy;
import edu.rice.comp504.model.strategy.UpdateStrategyFac;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

/**
 * A store containing current Ball World.
 */
public class BallWorldStore {
    private PropertyChangeSupport pcs;
    private static Point dims;
    private static UpdateStrategyFac ONLYms;
    private static CollisionStrategyFac ONLYcs;
    private static CommandFac ONLYc;

    /**
     * Constructor.
     */
    public BallWorldStore() {
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Get the singleton strategy factory.
     * @return The strategy factory
     */
    public static UpdateStrategyFac getOnlyMSFac() {
        if (ONLYms == null) {
            ONLYms = new UpdateStrategyFac();
        }
        return ONLYms;
    }

    /**
     * Get the singleton strategy factory.
     * @return The strategy factory
     */
    public static CollisionStrategyFac getOnlyCSFac() {
        if (ONLYcs == null) {
            ONLYcs = new CollisionStrategyFac();
        }
        return ONLYcs;
    }

    /**
     * Get the singleton strategy factory.
     * @return The strategy factory
     */
    public static CommandFac getOnlyCmdFac() {
        if (ONLYc == null) {
            ONLYc = new CommandFac();
        }
        return ONLYc;
    }

    /**
     * Get the canvas dimensions.
     * @return The canvas dimensions
     */
    public static Point getCanvasDims() {
        return dims;
    }

    /**
     * Set the canvas dimensions.
     * @param dim The canvas width (x) and height (y).
     */
    public static void setCanvasDims(String dim) {
        String x = dim.split("&")[0].split("=")[1];
        String y = dim.split("&")[1].split("=")[1];
        dims = new Point(Integer.parseInt(x), Integer.parseInt(y));
    }

    /**
     * Call the update method on all the ball observers to update their position in the ball world.
     */
    public PropertyChangeListener[] updateBallWorld() {
        pcs.firePropertyChange("clock", null,
                BallWorldStore.getOnlyCmdFac().make("update", pcs.getPropertyChangeListeners("clock")));
        return pcs.getPropertyChangeListeners("clock");
    }

    /**
     * Load a ball into the Ball World.
     * @param body  The REST request body has the strategy names.
     * @return A ball
     */
    public APaintObject loadObject(String body, String type) {
        APaintObject object;
        switch (type) {
            case "ball":
                object = new ObjectFac().make(body, type);
                break;
            case "sball":
                object = new ObjectFac().make(body + ",default", "ball");
                addBallToStore("sball", object);
                break;
            case "sfish":
                object = new ObjectFac().make(body + ",default", "image");
                addBallToStore("sball", object);
                break;
            case "image":
                object = new ObjectFac().make(body + ",default", type);
                break;
            default:
                object = new ObjectFac().make(body, "null");
        }
        if (object != null) {
            addBallToStore("clock", object);
        }
        return object;
    }


    /**
     * Switch the strategy of switcher balls
     * @param body  The REST request strategy.
     */
    public PropertyChangeListener[] switchStrategy(String body) {
        String[] strat = body.split(",");
        if (pcs.getPropertyChangeListeners("switchs").length == 0) {
            pcs.addPropertyChangeListener("switchs", SwitchStrategy.make(strat[0], strat[1]));
        } else {
            ((SwitchStrategy) pcs.getPropertyChangeListeners("switchs")[0]).setFrom(strat[0]);
            ((SwitchStrategy) pcs.getPropertyChangeListeners("switchs")[0]).setTo(strat[1]);
        }

        pcs.firePropertyChange("sball", null,
                BallWorldStore.getOnlyCmdFac().make("switch", pcs.getPropertyChangeListeners("switchs")));
        return null;
    }

    /**
     * Add a ball that will listen for a property change (i.e. time elapsed)
     * @param pcl  The ball
     */
    private void addBallToStore(String propertyName, PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(propertyName, pcl);
    }

    /**
     * Remove all balls from listening for property change events for a particular property.
     */
    public void removeBallsFromStore() {
        int l = pcs.getPropertyChangeListeners().length;
        for (int i = 0; i < l; ++i) {
            pcs.removePropertyChangeListener(pcs.getPropertyChangeListeners()[0]);
        }
    }

    /**
     * Remove few balls from listening for property change events for a particular property.
     */
    public void removeFewBallsFromStore(String strat) {
        for (PropertyChangeListener p: pcs.getPropertyChangeListeners("clock")) {
            if (((APaintObject) p).getMovementStrategy().getName().equals(strat) || ((APaintObject) p).getMovementStrategy().getName().equals("null")) {
                pcs.removePropertyChangeListener("clock", p);
            }
        }
    }

    /**
     * Pick a random color which is not the previous color of the shape.
     * @return Random Color
     */
    public static String randomColor(String color) {
        String[] availColors = {"red", "blue", "yellow", "green", "black", "purple", "orange", "gray", "brown"};

        int rand = new Random().nextInt(availColors.length);
        if (color == null) {
            return availColors[rand];
        } else {
            while (true) {
                if (!availColors[rand].equals(color)) {
                    return availColors[rand];
                }
                rand = new Random().nextInt(availColors.length);
            }
        }
    }
}
