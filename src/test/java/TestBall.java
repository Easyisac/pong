import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBall {

    private final int topLim = GameProperties.GAME_COURT_TOP_LIMIT;
    private final int botLim = GameProperties.GAME_COURT_BOTTOM_LIMIT;
    private final int leftLim = GameProperties.GAME_COURT_LEFT_LIMIT;
    private final int rightLim = GameProperties.GAME_COURT_RIGHT_LIMIT;

    private final Player pl0 = new Player("Player0", 0);
    private final Player pl1 = new Player("Player1", 1);

    private final Paddle pLeft = new Paddle(pl0);
    private final Paddle pRight = new Paddle(pl1);
    private final Ball ball = new Ball(pLeft, pRight, pl0, pl1, 5);
    private final int xStart = ball.getxPositionStart();
    private final int yStart = ball.getyPositionStart();
    private final double eps = 1E-5;

    @ParameterizedTest
    @CsvSource({"1, 1", "5, 5", "10,20", "25, 30"})
    public void ball_movement_inside_panel(double velAngle, double velModule) {
        ball.setVelocityAngle(velAngle);
        ball.setVelModule(velModule);
        ball.move();

        assertEquals(xStart + Math.cos(velAngle) * velModule, ball.getxPosition(), eps);
        assertEquals(yStart + Math.sin(velAngle) * velModule, ball.getyPosition(), eps);
    }

    @ParameterizedTest
    @CsvSource({"1, 1", "5, 5", "-10,20", "25, -30"})
    public void ball_reset(double velAngle, double velModule) {
        ball.setVelocityAngle(velAngle);
        ball.setVelModule(velModule);
        ball.move();
        ball.reset(1);

        assertEquals(xStart, ball.getxPosition());
        assertEquals(yStart, ball.getyPosition());
    }

    @ParameterizedTest
    @ValueSource(ints={10, 20, 50, 250, 500})
    public void ball_out_top_boundary(double velModule) {
        ball.setVelModule(velModule);
        ball.setVelocityAngle( 3/2.0 * Math.PI);
        ball.setyPosition(topLim + 15);

        assertTrue(ball.checkCollisionsTopLimit());
    }

    @ParameterizedTest
    @ValueSource(ints={10, 20, 50, 250, 500})
    public void ball_out_bottom_boundary(double velModule) {
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI/2);
        ball.setyPosition(botLim - 15);

        assertTrue(ball.checkCollisionsBottomLimit());
    }

    @ParameterizedTest
    @ValueSource(ints={5, 10, 50, 250, 500})
    public void ball_out_left_boundary(double velModule) {
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI);
        ball.setxPosition(leftLim + 15);

        assertTrue(ball.checkCollisionsLeftLimit());
    }

    @ParameterizedTest
    @ValueSource(ints={5, 10, 50, 250, 500})
    public void ball_out_right_boundary(double velModule) {
        ball.setVelModule(velModule);
        ball.setVelocityAngle(0);
        ball.setxPosition(rightLim - 15);

        assertTrue(ball.checkCollisionsRightLimit());
    }

    @ParameterizedTest
    @CsvSource({"0.6, 2", "0.7, 4", "1, 6", "1.1, 7", "1.2, 8"})
    public void ball_collides_with_paddle_left(double velAngle, double velModule) {
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI*velAngle);
        ball.setyPosition(pLeft.getyPosition() + pLeft.getPADDLE_HEIGHT()/2.0);
        ball.setxPosition(pLeft.getxPosition() + pLeft.getPADDLE_WIDTH() + 10);

        assertTrue(ball.checkCollisionsPaddleLeft());
    }

    @ParameterizedTest
    @CsvSource({"0.1, 2", "0.3, 4", "0.4, 6", "1.6, 7", "1.9, 8"})
    public void ball_collides_with_paddle_right(double velAngle, double velModule) {
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI*velAngle);
        ball.setyPosition(pRight.getyPosition() + pRight.getPADDLE_HEIGHT()/2.0);
        ball.setxPosition(pRight.getxPosition() - 10);

        assertTrue(ball.checkCollisionsPaddleRight());
    }

    @ParameterizedTest
    @CsvSource({"1.1, 1", "1.2, 2", "1.3, 3", "1.5, 4", "1.8, 5"})
    public void ball_bounced_top_boundary(double velAngle, double velModule){
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI*velAngle);
        ball.setyPosition(topLim+10);
        ball.setxPosition((rightLim+leftLim)/2.0);

        double newAngle = 2 * Math.PI - ball.getVelocityAngle();
        double final_positionX = ball.getxPosition() + Math.cos(newAngle) * velModule;
        double final_positionY = ball.getyPosition() + Math.sin(newAngle) * velModule;

        ball.move();

        assertEquals(final_positionX, ball.getxPosition(), eps);
        assertEquals(final_positionY, ball.getyPosition(), eps);
    }


    @ParameterizedTest
    @CsvSource({"0.1, 1", "0.4, 2", "0.5, 3", "0.7, 4", "0.9, 5"})
    public void ball_bounced_bottom_boundary(double velAngle, double velModule){
        ball.setVelModule(velModule);
        ball.setVelocityAngle(Math.PI*velAngle);
        ball.setyPosition(topLim-10);
        ball.setxPosition((rightLim+leftLim)/2.0);

        double newAngle = 2 * Math.PI - ball.getVelocityAngle();
        double final_positionX = ball.getxPosition() + Math.cos(newAngle) * velModule;
        double final_positionY = ball.getyPosition() + Math.sin(newAngle) * velModule;

        ball.move();

        assertEquals(final_positionX, ball.getxPosition(), eps);
        assertEquals(final_positionY, ball.getyPosition(), eps);
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
        ball.setVelocityAngle(Math.PI);
        ball.setyPosition(topLim+10);
        ball.setxPosition(leftLim+10);

        ball.move();

        assertEquals(ball.getxPositionStart(), ball.getxPosition(), eps);
        assertEquals(ball.getyPositionStart(), ball.getyPosition(), eps);
    }

    @ParameterizedTest
    @ValueSource(ints={1, 2, 3, 4, 6})
    public void ball_score_on_right_on_straight_line(double velModule){
        ball.setVelModule(velModule);
        ball.setVelocityAngle(0);
        ball.setyPosition(topLim+10);
        ball.setxPosition(rightLim-10);

        ball.move();

        assertEquals(ball.getxPositionStart(), ball.getxPosition(), eps);
        assertEquals(ball.getyPositionStart(), ball.getyPosition(), eps);
    }



/*    @ParameterizedTest
    @CsvSource({"10, 50", "50, 100", "100, 30"})
    public void ball_does_not_collide_with_top_boundary(int x, int y) {
        ball.setX(x);
        ball.setY(y);

        assertEquals(false, ball.checkCollisionsTop());
    }*/
}
