package edu.rice.comp504.model.ball;

/**
 * A factory that makes balls.
 */
public interface IObjectFac {
    /**
     * Makes a ball.
     * @return A Ball
     */
    APaintObject make(String strategy, String type);
}
