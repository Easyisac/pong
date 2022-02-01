import java.awt.*;

public class BallDrawer {

    private Ball ball;
    private int ballRadius;

    public BallDrawer(Ball ball) {

        this.ball = ball;
        ballRadius = ball.getBALL_RADIUS();
    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.red);
        g2.fillRect(ball.getX(), ball.getY(), ballRadius*2, ballRadius*2); //ball height and width missing
    }
}
