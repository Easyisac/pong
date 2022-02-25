package drawers;

import entities.Ball;
import java.awt.*;

public class BallDrawer implements Drawer {

    private final Ball ball;
    private final int ballRadius;

    public BallDrawer(Ball ball) {
        this.ball = ball;
        ballRadius = ball.getBALL_RADIUS();
    }

    public void draw(Graphics2D g2) {
        g2.fillOval((int) ball.getxCenter() - ballRadius, (int) ball.getyCenter() - ballRadius, ballRadius * 2, ballRadius * 2);
    }
}
