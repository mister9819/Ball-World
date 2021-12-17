package edu.rice.comp504.model;

import edu.rice.comp504.model.ball.APaintObject;
import edu.rice.comp504.model.ball.ObjectFac;
import edu.rice.comp504.model.ball.Image;
import junit.framework.TestCase;

import java.awt.*;

import static org.junit.Assert.assertNotEquals;

public class BallWorldStoreTest extends TestCase {
    Point dims;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        BallWorldStore.setCanvasDims("x=800&y=800");
        dims = BallWorldStore.getCanvasDims();
    }

    public APaintObject object1(APaintObject ball) {
        ball.setLocation(new Point(395, 400));
        ball.setHeight(10);
        ball.setWidth(10);
        ball.setVelocity(new Point(10, 0));
        return ball;
    }

    public APaintObject object2(APaintObject ball) {
        ball.setLocation(new Point(405, 400));
        ball.setHeight(10);
        ball.setWidth(10);
        ball.setVelocity(new Point(-10, 0));
        return ball;
    }

    public APaintObject object3(APaintObject ball) {
        ball.setLocation(new Point(400, 395));
        ball.setHeight(10);
        ball.setWidth(10);
        ball.setVelocity(new Point(0, 10));
        return ball;
    }

    public APaintObject object4(APaintObject ball) {
        ball.setLocation(new Point(400, 405));
        ball.setHeight(10);
        ball.setWidth(10);
        ball.setVelocity(new Point(0, -10));
        return ball;
    }

    public APaintObject rightWall(APaintObject ball) {
        ball.setLocation(new Point(799, 400));
        return ball;
    }

    public APaintObject leftWall(APaintObject ball) {
        ball.setLocation(new Point(1, 400));
        return ball;
    }

    public APaintObject topWall(APaintObject ball) {
        ball.setLocation(new Point(400, 1));
        return ball;
    }

    public APaintObject bottomWall(APaintObject ball) {
        ball.setLocation(new Point(400, 799));
        return ball;
    }

    public void testLoadObject() {
        APaintObject ball = new ObjectFac().make("rotate,selfdestruct", "ball");
        assertNotNull(ball);
        assertEquals("load vertical-horizontal ball type test",
                "rotate", ball.getMovementStrategy().getName());

        APaintObject fish = new ObjectFac().make("sizechange,default", "image");
        assertNotNull(fish);
        assertEquals("load sizechange fish type test",
                "sizechange", fish.getMovementStrategy().getName());
    }

    public void testAddObject() {
        BallWorldStore ballWorldStore = new BallWorldStore();
        int ballWorldCount = ballWorldStore.updateBallWorld().length;
        int testCount = 0;

        assertEquals("no ball test", testCount, ballWorldCount);

        ballWorldStore.loadObject("verhor", "fish");
        ballWorldCount = ballWorldStore.updateBallWorld().length;
        testCount += 1;
        assertEquals("add straight fish test", testCount, ballWorldCount);

        ballWorldStore.loadObject("bounce,colorchange", "ball");
        ballWorldCount = ballWorldStore.updateBallWorld().length;
        testCount += 1;
        assertEquals("add rotate ball test", testCount, ballWorldCount);

        ballWorldStore.loadObject("colorchange,sizechange", "ball");
        ballWorldCount = ballWorldStore.updateBallWorld().length;
        testCount += 1;
        assertEquals("add rotate ball test", testCount, ballWorldCount);

        ballWorldStore.loadObject("bounce,colorchange", "ball");
        ballWorldCount = ballWorldStore.updateBallWorld().length;
        testCount += 1;
        assertEquals("add rotate ball test", testCount, ballWorldCount);

        ballWorldStore.loadObject("null,null", "ball");
        ballWorldCount = ballWorldStore.updateBallWorld().length;
        testCount += 1;
        assertEquals("add rotate ball test", testCount, ballWorldCount);
    }

    public void testRemoveObjects() {
        BallWorldStore ballWorldStore = new BallWorldStore();

        ballWorldStore.loadObject("zigzag,destroyer", "ball");
        ballWorldStore.loadObject("blinking,colorinfector", "ball");

        ballWorldStore.removeBallsFromStore();
        int ballWorldCount = ballWorldStore.updateBallWorld().length;

        assertEquals("remove balls test", 0, ballWorldCount);
    }

    public void testSwitchObject() {
        BallWorldStore ballWorldStore = new BallWorldStore();

        APaintObject ball = ballWorldStore.loadObject("verhor", "sball");

        ballWorldStore.switchStrategy("verhor,rotate");
        assertEquals("Ball switched to rotate test", "rotate", ball.getMovementStrategy().getName());
        ballWorldStore.switchStrategy("rotate,zigzag");
        assertEquals("Ball switched to zigzag test", "zigzag", ball.getMovementStrategy().getName());
        ballWorldStore.switchStrategy("zigzag,verhor");
        assertEquals("Ball switched to straight test", "verhor", ball.getMovementStrategy().getName());

        APaintObject fish = ballWorldStore.loadObject("verhor", "sfish");

        ballWorldStore.switchStrategy("verhor,rotate");
        assertEquals("Fish switched to rotate test", "rotate", fish.getMovementStrategy().getName());
        ballWorldStore.switchStrategy("rotate,zigzag");
        assertEquals("Fish switched to zigzag test", "zigzag", fish.getMovementStrategy().getName());
        ballWorldStore.switchStrategy("zigzag,verhor");
        assertEquals("Fish switched to straight test", "verhor", fish.getMovementStrategy().getName());
    }

    public void testRemoveFewObjects() {
        BallWorldStore ballWorldStore = new BallWorldStore();

        ballWorldStore.loadObject("velchange,supercolorinfector", "ball");
        ballWorldStore.loadObject("velchange,supercolorinfector", "ball");
        ballWorldStore.loadObject("zigzagcolor,supercolorinfector", "ball");
        ballWorldStore.loadObject("velchange,supercolorinfector", "ball");
        ballWorldStore.loadObject("bouncevel,default", "ball");
        ballWorldStore.loadObject("zigzagcolor,default", "ball");
        ballWorldStore.loadObject("zigzagvel,default", "ball");
        ballWorldStore.loadObject("colorsizechange,default", "ball");
        ballWorldStore.loadObject("sizevelchange,default", "ball");
        ballWorldStore.loadObject("colorvelchange,default", "ball");

        ballWorldStore.removeFewBallsFromStore("velchange");
        int ballWorldCount = ballWorldStore.updateBallWorld().length;

        assertEquals("remove few balls of strategy velchange test", 7, ballWorldCount);

    }

    public void testBallWallCollision() {
        BallWorldStore ballWorldStore = new BallWorldStore();

        APaintObject ball = ballWorldStore.loadObject("verhor,default", "ball");
        Point vel = new Point(ball.getVelocity().x, ball.getVelocity().y);

        ball = rightWall(ball);
        Point new_loc = new Point(dims.x - ball.getWidth(), ball.getLocation().y + vel.y);
        ballWorldStore.updateBallWorld();

        assertEquals("update ball right wall location test", new_loc, ball.getLocation());
        assertEquals("update ball right wall velocity test", -vel.x, ball.getVelocity().x);

        ball = leftWall(ball);
        new_loc = new Point(ball.getWidth(), ball.getLocation().y + vel.y);
        ballWorldStore.updateBallWorld();

        assertEquals("update ball left wall location test", new_loc, ball.getLocation());
        assertEquals("update ball left wall velocity test", vel.x, ball.getVelocity().x);

        ball = bottomWall(ball);
        new_loc = new Point(ball.getLocation().x + vel.x, dims.y - ball.getWidth());
        ballWorldStore.updateBallWorld();

        assertEquals("update ball bottom wall location test", new_loc, ball.getLocation());
        assertEquals("update ball bottom wall velocity test", -vel.y, ball.getVelocity().y);

        ball = topWall(ball);
        new_loc = new Point(ball.getLocation().x + vel.x, ball.getWidth());
        ballWorldStore.updateBallWorld();

        assertEquals("update ball top wall location test", new_loc, ball.getLocation());
        assertEquals("update ball top wall velocity test", vel.y, ball.getVelocity().y);
    }

    public void testFishWallCollision() {
        BallWorldStore ballWorldStore = new BallWorldStore();

        APaintObject fish = ballWorldStore.loadObject("verhor,default", "image");
        Point vel = new Point(fish.getVelocity().x, fish.getVelocity().y);

        fish = rightWall(fish);
        assertEquals("fish direction test before collision", 1, ((Image) fish).getDirection());
        Point new_loc = new Point(dims.x - fish.getWidth() / 2, fish.getLocation().y + vel.y);
        ballWorldStore.updateBallWorld();

        assertEquals("update fish right wall location test", new_loc, fish.getLocation());
        assertEquals("update fish right wall velocity test", -vel.x, fish.getVelocity().x);
        assertEquals("fish direction test after collision", -1, ((Image) fish).getDirection());

        fish = leftWall(fish);
        assertEquals("fish direction test before collision", -1, ((Image) fish).getDirection());
        new_loc = new Point(fish.getWidth() / 2, fish.getLocation().y + vel.y);
        ballWorldStore.updateBallWorld();

        assertEquals("update fish left wall location test", new_loc, fish.getLocation());
        assertEquals("update fish left wall velocity test", vel.x, fish.getVelocity().x);
        assertEquals("fish direction test", 1, ((Image) fish).getDirection());

        fish = bottomWall(fish);
        new_loc = new Point(fish.getLocation().x + vel.x, dims.y - fish.getHeight() / 2);
        ballWorldStore.updateBallWorld();

        assertEquals("update fish bottom wall location test", new_loc, fish.getLocation());
        assertEquals("update fish bottom wall velocity test", -vel.y, fish.getVelocity().y);

        fish = topWall(fish);
        new_loc = new Point(fish.getLocation().x + vel.x, fish.getHeight() / 2);
        ballWorldStore.updateBallWorld();

        assertEquals("update fish top wall location test", new_loc, fish.getLocation());
        assertEquals("update fish top wall velocity test", vel.y, fish.getVelocity().y);
    }

    public void testBallBallCollision() {
        BallWorldStore ballWorldStore = new BallWorldStore();

        // Color change collision
        APaintObject ball1 = ballWorldStore.loadObject("verhor,colorchange", "ball");
        APaintObject ball2 = ballWorldStore.loadObject("verhor,default", "ball");

        ball1 = object1(ball1);
        ball2 = object2(ball2);
        String cur_color = ball1.getColor();

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertNotEquals("color change on collision test", cur_color, ball1.getColor());
        ballWorldStore.removeBallsFromStore();


        // Size change collision
        ball1 = ballWorldStore.loadObject("verhor,sizechange", "ball");
        ball2 = ballWorldStore.loadObject("verhor,default", "ball");

        // Size increase
        ball1 = object1(ball1);
        ball2 = object2(ball2);
        int new_radius = ball1.getWidth() + 10;

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertEquals("size change on collision test", new_radius, ball1.getWidth());

        // Size decrease
        ball1 = object1(ball1);
        ball2 = object2(ball2);
        new_radius = ball1.getWidth() - 10;

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertEquals("size change on collision test", new_radius, ball1.getWidth());
        ballWorldStore.removeBallsFromStore();


        // Self destruct collision
        ball1 = ballWorldStore.loadObject("verhor,selfdestruct", "ball");
        ball2 = ballWorldStore.loadObject("verhor,default", "ball");

        ball1 = object1(ball1);
        ball2 = object2(ball2);

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertEquals("self destruct on collision test", "null", ball1.getMovementStrategy().getName());
        ballWorldStore.removeBallsFromStore();


        // Self destruct collision
        ball1 = ballWorldStore.loadObject("verhor,destroyer", "ball");
        ball2 = ballWorldStore.loadObject("verhor,default", "ball");

        ball1 = object1(ball1);
        ball2 = object2(ball2);

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertEquals("make other ball null on collision test", "null", ball2.getMovementStrategy().getName());
        ballWorldStore.removeBallsFromStore();


        // color infector collision
        ball1 = ballWorldStore.loadObject("verhor,colorinfector", "ball");
        ball2 = ballWorldStore.loadObject("verhor,default", "ball");

        ball1 = object1(ball1);
        ball2 = object2(ball2);

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertEquals("make other ball same color collision test", ball1.getColor(), ball2.getColor());
        ballWorldStore.removeBallsFromStore();

        // super color infector collision
        ball1 = ballWorldStore.loadObject("verhor,supercolorinfector", "ball");
        ball2 = ballWorldStore.loadObject("verhor,default", "ball");

        ball1 = object1(ball1);
        ball2 = object2(ball2);

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertEquals("make other ball same color and  collision test",
                ball1.getColor(), ball2.getColor());
        assertEquals("give other ball color infector collision test",
                "supercolorinfector", ball2.getInteractionStrategy().getName());
        ballWorldStore.removeBallsFromStore();


        // Disappear and reappear collision
        ball1 = ballWorldStore.loadObject("verhor,blinking", "ball");
        ball2 = ballWorldStore.loadObject("verhor,default", "ball");

        // Disappear
        ball1 = object1(ball1);
        ball2 = object2(ball2);
        cur_color = ball1.getColor();

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertEquals("disappear on collision test", "white", ball1.getColor());

        // Reappear
        ball1 = object1(ball1);
        ball2 = object2(ball2);

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertEquals("reappear on collision test", cur_color, ball1.getColor());
        ballWorldStore.removeBallsFromStore();


        // velocity change faster collision
        ball1 = ballWorldStore.loadObject("verhor,velchange", "ball");
        ball2 = ballWorldStore.loadObject("verhor,velchange", "ball");

        ball1 = object1(ball1);
        ball2 = object2(ball2);
        int velx1 = -(ball1.getVelocity().x + 5);
        int velx2 = ball2.getVelocity().x - 5;

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertEquals("velocity change collision test", velx1, ball1.getVelocity().x);
        assertEquals("velocity change collision test", velx2, ball1.getVelocity().x);

        // velocity change slower collision
        // Have to make multiple collisions to reach the slower bit
        ball1 = object1(ball1);
        ball2 = object2(ball2);
        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();
        ball1 = object1(ball1);
        ball2 = object2(ball2);
        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();
        ball1 = object1(ball1);
        ball2 = object2(ball2);
        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        ball1 = object1(ball1);
        ball2 = object2(ball2);
        velx1 = -(ball1.getVelocity().x - 5);
        velx2 = ball2.getVelocity().x + 5;

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertEquals("velocity1 change collision test", velx1, ball1.getVelocity().x);
        assertEquals("velocity2 change collision test", velx2, ball1.getVelocity().x);
        ballWorldStore.removeBallsFromStore();


        // color and size change collision
        ball1 = ballWorldStore.loadObject("verhor,colorsizechange", "ball");
        ball2 = ballWorldStore.loadObject("verhor,default", "ball");

        ball1 = object1(ball1);
        ball2 = object2(ball2);
        cur_color = ball1.getColor();
        new_radius = ball1.getWidth() + 10;

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertNotEquals("color change on collision test", cur_color, ball1.getColor());
        assertEquals("size change on collision test", new_radius, ball1.getWidth());
        ballWorldStore.removeBallsFromStore();


        // color and velocity change collision
        ball1 = ballWorldStore.loadObject("verhor,colorvelchange", "ball");
        ball2 = ballWorldStore.loadObject("verhor,default", "ball");

        ball1 = object1(ball1);
        ball2 = object2(ball2);
        cur_color = ball1.getColor();
        velx1 = -(ball1.getVelocity().x + 5);

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertNotEquals("color change on collision test", cur_color, ball1.getColor());
        assertEquals("velocity change collision test", velx1, ball1.getVelocity().x);
        ballWorldStore.removeBallsFromStore();


        // size and velocity change collision
        ball1 = ballWorldStore.loadObject("verhor,sizevelchange", "ball");
        ball2 = ballWorldStore.loadObject("verhor,default", "ball");

        ball1 = object1(ball1);
        ball2 = object2(ball2);
        new_radius = ball1.getWidth() + 10;
        velx1 = -(ball1.getVelocity().x + 5);

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertEquals("size change on collision test", new_radius, ball1.getWidth());
        assertEquals("velocity change collision test", velx1, ball1.getVelocity().x);
        ballWorldStore.removeBallsFromStore();


        // color, size and velocity change collision
        ball1 = ballWorldStore.loadObject("verhor,colorsizevelchange", "ball");
        ball2 = ballWorldStore.loadObject("verhor,default", "ball");

        ball1 = object1(ball1);
        ball2 = object2(ball2);
        cur_color = ball1.getColor();
        new_radius = ball1.getWidth() + 10;
        velx1 = -(ball1.getVelocity().x + 5);

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertNotEquals("color change on collision test", cur_color, ball1.getColor());
        assertEquals("size change on collision test", new_radius, ball1.getWidth());
        assertEquals("velocity change collision test", velx1, ball1.getVelocity().x);
        ballWorldStore.removeBallsFromStore();


        // null strategy collision
        ball1 = ballWorldStore.loadObject("verhor,null", "ball");
        ball2 = ballWorldStore.loadObject("verhor,default", "ball");

        ball1 = object1(ball1);
        ball2 = object2(ball2);

        ballWorldStore.updateBallWorld();
        ballWorldStore.updateBallWorld();

        assertEquals("nothing on collision test", "null", ball1.getInteractionStrategy().getName());
        ballWorldStore.removeBallsFromStore();
    }

    public void testUpdateBallWorld() {
        BallWorldStore ballWorldStore = new BallWorldStore();

        // Update rotate ball
        APaintObject ball = ballWorldStore.loadObject("rotate,default", "ball");

        ball = object1(ball);
        Point vel = ball.getVelocity();
        Point nloc = ball.getLocation();
        double angle = Math.PI / 8;

        Point nvel = new Point((int) Math.round(vel.x * Math.cos(angle) - vel.y * Math.sin(angle)),
                (int) Math.round(vel.y * Math.cos(angle) + vel.x * Math.sin(angle)));
        nloc.x += nvel.x;
        nloc.y += nvel.y;

        ballWorldStore.updateBallWorld();

        assertEquals("update rotate ball velocity test", nvel, ball.getVelocity());
        assertEquals("update rotate ball location test", nloc, ball.getLocation());
        ballWorldStore.removeBallsFromStore();


        // Update ellipse ball
        ball = ballWorldStore.loadObject("ellipse,default", "ball");

        ball = object1(ball);
        vel = ball.getVelocity();
        nloc = ball.getLocation();

        nvel = new Point((int) Math.round(vel.x * Math.cos(angle) - vel.y * Math.sin(angle)),
                (int) Math.round(vel.y * Math.cos(angle) + vel.x * Math.sin(angle)));
        nloc.x += nvel.x;
        nloc.x += nvel.x;
        nloc.y += nvel.y;

        ballWorldStore.updateBallWorld();

        assertEquals("update ellipse ball velocity test", nvel, ball.getVelocity());
        assertEquals("update ellipse ball location test", nloc, ball.getLocation());
        ballWorldStore.removeBallsFromStore();


        // Update zigzag ball
        ball = ballWorldStore.loadObject("zigzag,default", "ball");

        ball = object1(ball);
        nloc = ball.getLocation();

        // zig = false
        nloc.x += ball.getVelocity().x;
        ballWorldStore.updateBallWorld();

        assertEquals("update zigzag ball location test", nloc, ball.getLocation());

        // zig = true
        nloc.y += ball.getVelocity().y;
        ballWorldStore.updateBallWorld();

        assertEquals("update zigzag ball location test", nloc, ball.getLocation());
        ballWorldStore.removeBallsFromStore();


        // Update bounce size ball
        ball = ballWorldStore.loadObject("bouncesize,default", "ball");

        ball = object1(ball);
        nloc = ball.getLocation();
        int new_radius = ball.getWidth() + 10;

        // bounce = false
        nloc.y += ball.getVelocity().y * 2;
        ballWorldStore.updateBallWorld();

        assertEquals("update bouncesize ball location test", nloc, ball.getLocation());
        assertEquals("update bouncesize ball size test", new_radius, ball.getHeight());

        // bounce = true
        nloc.y -= ball.getVelocity().y * 2;
        new_radius = ball.getWidth() - 10;
        ballWorldStore.updateBallWorld();

        assertEquals("update bouncesize ball location test", nloc, ball.getLocation());
        assertEquals("update bouncesize ball size test", new_radius, ball.getHeight());
        ballWorldStore.removeBallsFromStore();


        // Update color, size and velocity
        ball = ballWorldStore.loadObject("colorsizevelchange,default", "ball");

        ball = object1(ball);
        String cur_color = ball.getColor();
        new_radius = ball.getWidth() + 10;
        int velx1 = ball.getVelocity().x + 5;

        ballWorldStore.updateBallWorld();

        assertNotEquals("update color change test", cur_color, ball.getColor());
        assertEquals("update size change test", new_radius, ball.getWidth());
        assertEquals("update velocity change test", velx1, ball.getVelocity().x);
        ballWorldStore.removeBallsFromStore();


        // Update blinking ball
        ball = ballWorldStore.loadObject("blinking,default", "ball");

        ball = object1(ball);
        cur_color = ball.getColor();

        // blink = true
        ballWorldStore.updateBallWorld();

        assertEquals("update blinking ball color test", "white", ball.getColor());

        // blink = false
        ballWorldStore.updateBallWorld();

        assertEquals("update blinking ball color test", cur_color, ball.getColor());
        ballWorldStore.removeBallsFromStore();
    }
}