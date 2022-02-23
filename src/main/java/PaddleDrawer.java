import java.awt.*;

public class PaddleDrawer implements Drawer {

    private Paddle paddle;

    public PaddleDrawer(Paddle paddle) {
        this.paddle = paddle;
    }

    public void draw(Graphics2D g2){
        g2.fillRect((int)paddle.getxPosition(), (int)paddle.getyPosition(), paddle.getPADDLE_WIDTH(), paddle.getPADDLE_HEIGHT());
    }
}
