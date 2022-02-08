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
        g2.fillOval((int)ball.getX()-ballRadius, (int)ball.getY()-ballRadius, ballRadius*2, ballRadius*2);
    }
}
