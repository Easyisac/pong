import java.awt.*;

public class BallDrawer {

    private final Ball ball;
    private final int ballRadius;

    public BallDrawer(Ball ball) {

        this.ball = ball;
        ballRadius = ball.getBALL_RADIUS();
    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.red);
        g2.fillOval((int)ball.getxPosition()-ballRadius, (int)ball.getyPosition()-ballRadius, ballRadius*2, ballRadius*2);
    }
}
