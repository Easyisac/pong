import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBall {

    private final int topLim = 0;
    private final int botLim = 500;
    private final int leftLim = 0;
    private final int rightLim = 500;

    private final Player pl0 = new Player("Player0", 0);
    private final Player pl1 = new Player("Player1", 1);

    private final Paddle pLeft = new Paddle(pl0, topLim, botLim, leftLim, rightLim);
    private final Paddle pRight = new Paddle(pl1, topLim, botLim, leftLim, rightLim);
    private final Ball ball = new Ball(topLim, botLim, leftLim, rightLim, pLeft, pRight, pl0, pl1);
    private final int xStart = ball.getxStart();
    private final int yStart = ball.getyStart();
    private final double eps = 1E-5;

    @ParameterizedTest
    @CsvSource({"1, 1", "5, 5", "10,20", "25, 30"})
    public void ball_inside_panel_move_test(double velAngle, double velModule) {
        ball.setVelAngle(velAngle);
        ball.setVelModule(velModule);
        ball.move();

        assertEquals(xStart + Math.cos(velAngle) * velModule, ball.getX(), eps);
        assertEquals(yStart + Math.sin(velAngle) * velModule, ball.getY(), eps);
    }

    @ParameterizedTest
    @CsvSource({"1, 1", "5, 5", "-10,20", "25, -30"})
    public void ball_reset_test(double velAngle, double velModule) {
        ball.setVelAngle(velAngle);
        ball.setVelModule(velModule);
        ball.move();
        ball.reset(1);

        assertEquals(xStart, ball.getX());
        assertEquals(yStart, ball.getY());
    }

    @ParameterizedTest
    @ValueSource(ints={10, 20, 50, 250, 500})
    public void ball_out_top_boundary(double velModule) {
        ball.setVelModule(velModule);
        ball.setVelAngle( 3/2.0 * Math.PI);
        ball.setY(topLim + 15);

        assertTrue(ball.checkCollisionsTop());
    }

    @ParameterizedTest
    @ValueSource(ints={10, 20, 50, 250, 500})
    public void ball_out_bottom_boundary(double velModule) {
        ball.setVelModule(velModule);
        ball.setVelAngle(Math.PI/2);
        ball.setY(botLim - 15);

        assertTrue(ball.checkCollisionsBottom());
    }

    @ParameterizedTest
    @ValueSource(ints={5, 10, 50, 250, 500})
    public void ball_out_left_boundary(double velModule) {
        ball.setVelModule(velModule);
        ball.setVelAngle(Math.PI);
        ball.setX(leftLim + 15);

        assertTrue(ball.checkCollisionsLeft());
    }

    @ParameterizedTest
    @ValueSource(ints={5, 10, 50, 250, 500})
    public void ball_out_right_boundary(double velModule) {
        ball.setVelModule(velModule);
        ball.setVelAngle(0);
        ball.setX(rightLim - 15);

        assertTrue(ball.checkCollisionsRight());
    }

    @ParameterizedTest
    @CsvSource({"0.6, 2", "0.7, 4", "1, 6", "1.1, 7", "1.2, 8"})
    public void ball_collides_with_paddle_left(double velAngle, double velModule) {
        ball.setVelModule(velModule);
        ball.setVelAngle(Math.PI*velAngle);
        ball.setY(pLeft.getY() + pLeft.getPADDLE_HEIGHT()/2.0);
        ball.setX(pLeft.getX() + pLeft.getPADDLE_WIDTH() + 10);

        assertTrue(ball.checkCollisionsPaddleLeft());
    }

    @ParameterizedTest
    @CsvSource({"0.1, 2", "0.3, 4", "0.4, 6", "1.6, 7", "1.9, 8"})
    public void ball_collides_with_paddle_right(double velAngle, double velModule) {
        ball.setVelModule(velModule);
        ball.setVelAngle(Math.PI*velAngle);
        ball.setY(pRight.getY() + pRight.getPADDLE_HEIGHT()/2.0);
        ball.setX(pRight.getX() - 10);

        assertTrue(ball.checkCollisionsPaddleRight());
    }

    @ParameterizedTest
    @CsvSource({"1.1, 1", "1.2, 2", "1.3, 3", "1.5, 4", "1.8, 5"})
    public void ball_bounced_top_boundary(double velAngle, double velModule){
        ball.setVelModule(velModule);
        ball.setVelAngle(Math.PI*velAngle);
        ball.setY(topLim+10);
        ball.setX((rightLim+leftLim)/2.0);

        double newAngle = 2 * Math.PI - ball.getVelAngle();
        double final_positionX = ball.getX() + Math.cos(newAngle) * velModule;
        double final_positionY = ball.getY() + Math.sin(newAngle) * velModule;

        ball.move();

        assertEquals(final_positionX, ball.getX(), eps);
        assertEquals(final_positionY, ball.getY(), eps);
    }


    @ParameterizedTest
    @CsvSource({"0.1, 1", "0.4, 2", "0.5, 3", "0.7, 4", "0.9, 5"})
    public void ball_bounced_bottom_boundary(double velAngle, double velModule){
        ball.setVelModule(velModule);
        ball.setVelAngle(Math.PI*velAngle);
        ball.setY(topLim-10);
        ball.setX((rightLim+leftLim)/2.0);

        double newAngle = 2 * Math.PI - ball.getVelAngle();
        double final_positionX = ball.getX() + Math.cos(newAngle) * velModule;
        double final_positionY = ball.getY() + Math.sin(newAngle) * velModule;

        ball.move();

        assertEquals(final_positionX, ball.getX(), eps);
        assertEquals(final_positionY, ball.getY(), eps);
    }

/*
    @ParameterizedTest
    @CsvSource({"-10, 0", "-10, 10", "-15, 10", "-10, 25", "-10, -25"})
    public void ball_bounced_paddle_left(int xVelocity, int yVelocity){
        ball.setxVelocity(xVelocity);
        ball.setyVelocity(yVelocity);
        ball.setY(pLeft.getY() + pLeft.getPADDLE_HEIGHT()/2);
        ball.setX(pLeft.getX() + pLeft.getPADDLE_WIDTH() + 10);

        int final_positionX = ball.getX() - xVelocity;
        int final_positionY = ball.getY() + yVelocity;

        ball.move();

        assertEquals(final_positionX, ball.getX());
        assertEquals(final_positionY, ball.getY());
    }


    @ParameterizedTest
    @CsvSource({"10, 0", "10, 10", "15, 10", "10, 25", "10, -25"})
    public void ball_bounced_paddle_right(int xVelocity, int yVelocity){
        ball.setxVelocity(xVelocity);
        ball.setyVelocity(yVelocity);
        ball.setY(pRight.getY() + pRight.getPADDLE_HEIGHT()/2);
        ball.setX(pRight.getX() - 10);

        int final_positionX = ball.getX() - xVelocity;
        int final_positionY = ball.getY() + yVelocity;

        ball.move();

        assertEquals(final_positionX, ball.getX());
        assertEquals(final_positionY, ball.getY());
    }*/

    @ParameterizedTest
    @ValueSource(ints={1, 2, 3, 4, 6})
    public void ball_score_on_left_on_straight_line(double velModule){
        ball.setVelModule(velModule);
        ball.setVelAngle(Math.PI);
        ball.setY(topLim+10);
        ball.setX(leftLim+10);

        ball.move();

        assertEquals(ball.getxStart(), ball.getX(), eps);
        assertEquals(ball.getyStart(), ball.getY(), eps);
    }

    @ParameterizedTest
    @ValueSource(ints={1, 2, 3, 4, 6})
    public void ball_score_on_right_on_straight_line(double velModule){
        ball.setVelModule(velModule);
        ball.setVelAngle(0);
        ball.setY(topLim+10);
        ball.setX(rightLim-10);

        ball.move();

        assertEquals(ball.getxStart(), ball.getX(), eps);
        assertEquals(ball.getyStart(), ball.getY(), eps);
    }



/*    @ParameterizedTest
    @CsvSource({"10, 50", "50, 100", "100, 30"})
    public void ball_does_not_collide_with_top_boundary(int x, int y) {
        ball.setX(x);
        ball.setY(y);

        assertEquals(false, ball.checkCollisionsTop());
    }*/
}
