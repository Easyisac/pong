package drawers;

import entities.Ball;
import java.awt.*;

public class BallDrawer implements Drawer {

    private final Ball ball;

    public BallDrawer(Ball ball) {
        this.ball = ball;
    }

    public void draw(Graphics2D g2) {
        g2.fillOval((int) ball.getxCenter() - Ball.BALL_RADIUS, (int) ball.getyCenter() - Ball.BALL_RADIUS,
                Ball.BALL_RADIUS * 2, Ball.BALL_RADIUS * 2);
    }
}
