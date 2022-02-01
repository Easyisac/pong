import java.awt.*;

public class PaddleDrawer {
    private Paddle paddle;

    public PaddleDrawer(Paddle paddle) {
        this.paddle = paddle;
    }

    public void draw(Graphics2D g2){
        if(paddle.getId()==0){
            g2.setColor(Color.ORANGE);
        } else if(paddle.getId()==1){
            g2.setColor(Color.green);
        }
        g2.fillRect(paddle.getX(), paddle.getY(), paddle.getPADDLE_WIDTH(), paddle.getPADDLE_HEIGHT());
    }
}
