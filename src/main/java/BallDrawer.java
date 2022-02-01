import java.awt.*;

public class BallDrawer {

    private Ball ball;

    public BallDrawer(Ball ball) {
        this.ball = ball;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.red);
        g2.fillRect(ball.getX(), ball.getY(), 20, 20); //ball height and width missing
    }
}
