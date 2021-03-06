package drawers;

import entities.Paddle;
import java.awt.*;

public class PaddleDrawer implements Drawer {

    private final Paddle paddle;

    public PaddleDrawer(Paddle paddle) {
        this.paddle = paddle;
    }

    public void draw(Graphics2D g2) {
        g2.fillRect(paddle.getxPosition(), paddle.getyPosition(), Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT);
    }
}
