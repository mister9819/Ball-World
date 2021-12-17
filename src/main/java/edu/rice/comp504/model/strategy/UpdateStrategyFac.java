package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.strategy.common.*;
import edu.rice.comp504.model.strategy.movement.*;

/**
 * Factory that creates update object strategies.
 */
public class UpdateStrategyFac implements IStrategyFac {

    /**
     * Constructor.
     */
    public UpdateStrategyFac() {

    }

    @Override
    public IUpdateStrategy make(String type) {
        switch (type) {
//            case "border":
//                return BorderMovementStrategy.make();
            case "bounce":
                return BounceMovementStrategy.make();
            case "colorchange":
                return (IUpdateStrategy) ColorChangeStrategy.make();
            case "verhor":
                return CompositeMovementStrategy.make();
            case "rotate":
                return RotateMovementStrategy.make();
            case "sizechange":
                return (IUpdateStrategy) SizeChangeStrategy.make();
            case "zigzag":
                return ZigzagMovementStrategy.make();
            case "blinking":
                return (IUpdateStrategy) BlinkingStrategy.make();
            case "velchange":
                return (IUpdateStrategy) VelocityChangeStrategy.make();
//            case "halt":
//                return (IUpdateStrategy) HaltMovementStrategy.make();
            case "colorsizechange":
                return (IUpdateStrategy) ColorSizeChangeStrategy.make();
            case "sizevelchange":
                return (IUpdateStrategy) SizeVelocityChangeStrategy.make();
            case "colorvelchange":
                return (IUpdateStrategy) ColorVelocityChangeStrategy.make();
            case "colorsizevelchange":
                return (IUpdateStrategy) ColorSizeVelocityChangeStrategy.make();
            case "zigzagvel":
                return ZigzagMovementVelocityChangeStrategy.make();
            case "zigzagcolor":
                return ZigzagMovementColorChangeStrategy.make();
            case "bouncevel":
                return BounceMovementVelocityChangeStrategy.make();
            case "bouncesize":
                return BounceMovementSizeChangeStrategy.make();
            case "ellipse":
                return EllipseMovementStrategy.make();
            default:
                return (IUpdateStrategy) NullStrategy.make();
        }
    }
}
