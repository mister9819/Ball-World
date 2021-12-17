package edu.rice.comp504.model.strategy.movement;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

/**
 * The ellipse strategy potentially has multiple strategies as children.
 */
public class EllipseMovementStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;
    private IUpdateStrategy[] children;

    /**
     * Constructor.
     */
    private EllipseMovementStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }

    /**
     * Get the singleton ellipse strategy.
     * @return The singleton ellipse strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            IUpdateStrategy[] children = new IUpdateStrategy[2];
            children[0] = RotateMovementStrategy.make();
            children[1] = HorizontalMovementStrategy.make();
            ONLY = new EllipseMovementStrategy(children);
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "ellipse";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        for (IUpdateStrategy child: children) {
            child.updateState(context);
        }
    }
}
