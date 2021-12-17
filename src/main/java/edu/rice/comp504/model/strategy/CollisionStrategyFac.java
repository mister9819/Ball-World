package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.strategy.collision.*;
import edu.rice.comp504.model.strategy.common.*;

/**
 * Factory that creates interaction object strategies.
 */
public class CollisionStrategyFac implements IStrategyFac {

    /**
     * Constructor.
     */
    public CollisionStrategyFac() {

    }

    @Override
    public ICollisionStrategy make(String type) {
        switch (type) {
            case "colorchange":
                return (ICollisionStrategy) ColorChangeStrategy.make();
            case "default":
                return DefaultInteractionStrategy.make();
            case "sizechange":
                return (ICollisionStrategy) SizeChangeStrategy.make();
            case "selfdestruct":
                return (ICollisionStrategy) SelfDestructStrategy.make();
            case "destroyer":
                return (ICollisionStrategy) DestroyerStrategy.make();
            case "colorinfector":
                return ColorInfectorStrategy.make();
            case "supercolorinfector":
                return SuperColorInfectorStrategy.make();
            case "blinking":
                return (ICollisionStrategy) BlinkingStrategy.make();
            case "velchange":
                return (ICollisionStrategy) VelocityChangeStrategy.make();
//            case "halt":
//                return (ICollisionStrategy) HaltMovementStrategy.make();
            case "colorsizechange":
                return (ICollisionStrategy) ColorSizeChangeStrategy.make();
            case "sizevelchange":
                return (ICollisionStrategy) SizeVelocityChangeStrategy.make();
            case "colorvelchange":
                return (ICollisionStrategy) ColorVelocityChangeStrategy.make();
            case "colorsizevelchange":
                return (ICollisionStrategy) ColorSizeVelocityChangeStrategy.make();
            default:
                return (ICollisionStrategy) NullStrategy.make();
        }
    }
}
