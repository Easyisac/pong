import java.awt.*;

public class PaddleDrawer {
    Paddle paddle;

    public PaddleDrawer(Paddle paddle) {
        this.paddle = paddle;
    }

    public void draw(Graphics2D g2){
        g2.fillRect(paddle.getX(), paddle.getY(), paddle.getPADDLE_WIDTH(), paddle.getPADDLE_HEIGHT());
    }
}
