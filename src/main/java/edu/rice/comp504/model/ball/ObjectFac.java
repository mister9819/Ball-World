package edu.rice.comp504.model.ball;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;

/**
 * Factory to make objects.
 */
public class ObjectFac implements IObjectFac {
    /**
     * Makes objects with random location, velocity, color*.
     * @param strategy The movement and interaction strategy of the object
     * @param type The type of object to be made
     * @return The object, can be ball, image or null
     */
    @Override
    public APaintObject make(String strategy, String type) {
        Point limit = BallWorldStore.getCanvasDims();
        String[] strat = strategy.split(",");

        if (type.equals("ball")) {

            if (strat[0].equals("null")) {
                return NullObject.make();
            }

            int radius = RandUtil.getRnd(10, 30);
            Point loc = new Point(RandUtil.getRnd(radius, limit.x - 60),
                    RandUtil.getRnd(radius, limit.y - 60));
            String color = BallWorldStore.randomColor(null);
            Point vel = new Point(RandUtil.getRnd(2, 7), RandUtil.getRnd(2, 7));

            APaintObject ball = new Ball(loc, radius, vel, color,
                    BallWorldStore.getOnlyMSFac().make(strat[0]),
                    BallWorldStore.getOnlyCSFac().make(strat[1]));
            ball.getMovementStrategy().getName();
            ball.getInteractionStrategy().getName();
            return ball;
        } else if (type.equals("image")) {

            int size = RandUtil.getRnd(30, 50);
            Point loc = new Point(RandUtil.getRnd(size, limit.x - 60),
                    RandUtil.getRnd(size, limit.y - 60));
            Point vel = new Point(RandUtil.getRnd(6, 12), RandUtil.getRnd(6, 12));

            return new Image(loc, size, vel,
                    BallWorldStore.getOnlyMSFac().make(strat[0]),
                    BallWorldStore.getOnlyCSFac().make(strat[1]));
        } else {
            return new NullObject();
        }
    }
}
