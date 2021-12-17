package edu.rice.comp504.model.strategy.movement;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;


/**
 * The composite strategy potentially has multiple strategies as children.
 */
public class CompositeMovementStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;
    private IUpdateStrategy[] children;

    /**
     * Constructor.
     */
    private CompositeMovementStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }

    /**
     * Get the singleton composite strategy.
     * @return The singleton composite strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            IUpdateStrategy[] children = new IUpdateStrategy[2];
            children[0] = HorizontalMovementStrategy.make();
            children[1] = VerticalMovementStrategy.make();
            ONLY = new CompositeMovementStrategy(children);
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "verhor";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        for (IUpdateStrategy child: children) {
            child.updateState(context);
        }
//        Point vel = context.getVelocity();
//        Point nloc = context.getLocation();
//
//        nloc.x += vel.x;
//        nloc.y += vel.y;
//
//        context.setLocation(nloc);
    }
}
