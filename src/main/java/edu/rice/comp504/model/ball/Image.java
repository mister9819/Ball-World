package edu.rice.comp504.model.ball;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.strategy.ICollisionStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

public class Image extends APaintObject {

    private String src;
    private Point scale;


    /**
     * Constructor.
     *
     * @param loc    The this.location of the ball on the canvas
     * @param size   The image size
     * @param vel    The image velocity
     * @param strategy1 The movement strategy
     * @param strategy2 The interaction strategy
     */
    protected Image(Point loc, int size, Point vel, IUpdateStrategy strategy1, ICollisionStrategy strategy2) {
        super(loc, vel, size, Math.round(size * 396 / 512), "null", "image", strategy1, strategy2);
        src = "fish";
        scale = new Point(1,1);
    }

    /**
     * Detects collision between a image and a wall in the ball world. Change direction if image collides with a wall.
     * @return True if there was a collision and false otherwise.
     */
    @Override
    public boolean detectCollision() {
        boolean flag = false;
        // Right and left side of the box
        Point dims = BallWorldStore.getCanvasDims();
        if ((this.vel.x < 0) && (this.loc.x - this.width / 2 <= 0)) {
            this.vel.x *= -1;
            this.loc.x = width / 2;
            flag = true;
        } else if ((this.vel.x > 0) && (this.loc.x + this.width / 2 >= dims.x)) {
            this.vel.x *= -1;
            this.loc.x = dims.x - width / 2;
            flag = true;
        }
        // Bottom and top side of the box
        if ((this.vel.y < 0) && (this.loc.y - this.height / 2 <= 0)) {
            this.vel.y *= -1;
            this.loc.y = height / 2;
            flag = true;
        } else if ((this.vel.y > 0) && (this.loc.y + this.height / 2 >= dims.y)) {
            this.vel.y = this.vel.y * -1;
            this.loc.y = dims.y - height / 2;
            flag = true;
        }
        if (vel.x < 0) {
            scale.x = -1;
        } else {
            scale.x = 1;
        }
        return flag;
    }

    /**
     * Get the direction of which the image is facing.
     * @return 1 if facing right, -1 if facing left
     */
    public int getDirection() {
        return scale.x;
    }
}
