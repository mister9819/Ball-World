package edu.rice.comp504.model.ball;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.strategy.ICollisionStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

/**
 * The balls that will be drawn in the ball world.
 */
public class Ball extends APaintObject {

    /**
     * Constructor.
     * @param loc  The location of the ball on the canvas
     * @param radius The ball radius
     * @param vel  The ball velocity
     * @param color The ball color
     * @param strategy1 The movement strategy
     * @param strategy2 The interaction strategy
     */
    protected Ball(Point loc, int radius, Point vel, String color, IUpdateStrategy strategy1, ICollisionStrategy strategy2) {
        super(loc, vel, radius, radius, color, "ball", strategy1, strategy2);
    }

    /**
     * Detects collision between a ball and a wall in the ball world.  Change direction if ball collides with a wall.
     * @return True if there was a collision and false otherwise.
     */
    public boolean detectCollision() {
        boolean flag = false;
        Point dims = BallWorldStore.getCanvasDims();
        //Right and left side of the box
        if (dims.x < this.loc.x + this.width) {
            this.vel.x = -this.vel.x;
            this.loc.x = dims.x - this.width;
            flag = true;
        } else if (0 > this.loc.x - this.width) {
            this.vel.x = -this.vel.x;
            this.loc.x = this.width;
            flag = true;
        }
        //Bottom and top side of the box
        if (dims.y < this.loc.y + this.height) {
            this.vel.y = -this.vel.y;
            this.loc.y = dims.y - this.height;
            flag = true;
        } else if (0 > this.loc.y - this.height) {
            this.vel.y = -this.vel.y;
            this.loc.y = this.height;
            flag = true;
        }
        return flag;
    }

    /**
     * Check if the paint object is colorable.  For example, images are not colorable and would return false.
     */
    public boolean isColorable() {
        return true;
    }
}
