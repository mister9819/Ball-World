package edu.rice.comp504.model.ball;

import edu.rice.comp504.model.cmd.IObjCmd;
import edu.rice.comp504.model.strategy.ICollisionStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class APaintObject implements PropertyChangeListener {
    Point loc;
    Point vel;
    IUpdateStrategy movementStrategy;
    ICollisionStrategy interactionStrategy;
    String color;
    String type;
    int width;
    int height;

    /**
     * Constructor.
     * @param loc  The location of the paintable object on the canvas
     * @param vel  The object velocity
     * @param strategy1  The object update strategy
     */
    public APaintObject(Point loc, Point vel, int width, int height, String color, String type, IUpdateStrategy strategy1, ICollisionStrategy strategy2) {
        this.loc = loc;
        this.vel = vel;
        this.color = color;
        this.type = type;
        this.movementStrategy = strategy1;
        this.interactionStrategy = strategy2;
        this.width = width;
        this.height = height;
    }


    /**
     * Detects collision between a paint and a boundary wall. Change direction if ball collides with boundary.
     */
    public abstract boolean detectCollision();

    /**
     * Detects collision between an object and an object in the ball world. Change direction if ball collides with a ball.
     * @return True if there was a collision and false otherwise.
     */
    public boolean detectObjectCollision(APaintObject context) {
        Point cloc = context.getLocation();
        int cradius = context.getWidth();
        Point cvel = context.getVelocity();

        double xDif = this.loc.x - cloc.x;
        double yDif = this.loc.y - cloc.y;
        double distanceSquared = xDif * xDif + yDif * yDif;
        boolean collision = distanceSquared < (this.width + cradius) * (this.width + cradius);
        if (collision) {
            if (this instanceof Ball) {
                context.setVelocity(new Point(-cvel.x, -cvel.y));
                context.getMovementStrategy().updateState(context);
            }

            this.setVelocity(new Point(-vel.x, -vel.y));
            this.getMovementStrategy().updateState(this);
            if (this instanceof Image) {
                this.getMovementStrategy().updateState(this);
            }
        }
        return collision;
    }

    /**
     * Get the paint location in the paint world.
     * @return The paint location.
     */
    public Point getLocation() {
        return loc;
    }


    /**
     * Set the paint location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The paint coordinate.
     */
    public void setLocation(Point loc) {
        this.loc = loc;
    }

    /**
     * Check if the paint object is colorable.  For example, images are not colorable and would return false.
     */
    public boolean isColorable() {
        return false;
    }

    /**
     * Get the paintable object color.
     * @return The paintable object color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the paintable object color.
     * @param color The new color
     */
    public void setColor(String color) {
        if (isColorable()) {
            this.color = color;
        }
    }

    /**
     * Get the velocity of the paint.
     * @return The paint velocity
     */
    public  Point getVelocity() {
        return vel;
    }

    public void setVelocity(Point vel) {
        this.vel = vel;
    }

    /**
     * Get the paint object strategy.
     * @return The paint object strategy
     */
    public IUpdateStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public void setMovementStrategy(IUpdateStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ICollisionStrategy getInteractionStrategy() {
        return interactionStrategy;
    }

    public void setInteractionStrategy(ICollisionStrategy interactionStrategy) {
        this.interactionStrategy = interactionStrategy;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ((IObjCmd) evt.getNewValue()).execute(this);
    }
}
