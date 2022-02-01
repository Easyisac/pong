import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBall {

    private int topLim = 0;
    private int botLim = 500;
    private int leftLim = 0;
    private int rightLim = 500;

    private final Paddle pLeft = new Paddle(0, topLim, botLim, leftLim, rightLim);
    private final Paddle pRight = new Paddle(1, topLim, botLim, leftLim, rightLim);
    private final Ball ball = new Ball(topLim, botLim, leftLim, rightLim, pLeft, pRight);
    private final int xStart = ball.getxStart();
    private final int yStart = ball.getyStart();


    @ParameterizedTest
    @CsvSource({"1, 1", "5, 5", "-10,20", "25, -30"})
    public void ball_inside_panel_move_test(int xVelocity, int yVelocity) {
        ball.setxVelocity(xVelocity);
        ball.setyVelocity(yVelocity);
        ball.move();

        assertEquals(xStart + xVelocity, ball.getX());
        assertEquals(yStart + yVelocity, ball.getY());
    }

    @ParameterizedTest
    @CsvSource({"1, 1", "5, 5", "-10,20", "25, -30"})
    public void ball_reset_test(int xVelocity, int yVelocity) {
        ball.setxVelocity(xVelocity);
        ball.setyVelocity(yVelocity);
        ball.move();
        ball.reset();

        assertEquals(xStart, ball.getX());
        assertEquals(yStart, ball.getY());
    }

    @ParameterizedTest
    @ValueSource(ints={-10, -20, -50, -250, -500})
    public void ball_out_top_boundary(int yVelocity) {
        ball.setyVelocity(yVelocity);
        ball.setY(topLim + 15);

        assertEquals(true, ball.checkCollisionsTop());
    }

    @ParameterizedTest
    @ValueSource(ints={10, 20, 50, 250, 500})
    public void ball_out_bottom_boundary(int yVelocity) {
        ball.setyVelocity(yVelocity);
        ball.setY(botLim - 15);

        assertEquals(true, ball.checkCollisionsBottom());
    }

    @ParameterizedTest
    @ValueSource(ints={-5, -10, -50, -250, -500})
    public void ball_out_left_boundary(int xVelocity) {
        ball.setxVelocity(xVelocity); // ball hits boundary with left side
        ball.setX(leftLim + 15);

        assertEquals(true, ball.checkCollisionsLeft());
    }

    @ParameterizedTest
    @ValueSource(ints={5, 10, 50, 250, 500})
    public void ball_out_right_boundary(int xVelocity) {
        ball.setxVelocity(xVelocity); // ball hits boundary with right side
        ball.setX(rightLim - 15);

        assertEquals(true, ball.checkCollisionsRight());
    }

    @ParameterizedTest
    @CsvSource({"-10, 0", "-10, 10", "-15, 10", "-10, 25", "-10, -25"})
    public void ball_collides_with_paddle_left(int xVelocity, int yVelocity) {
        ball.setxVelocity(xVelocity);
        ball.setyVelocity(yVelocity);
        ball.setY(pLeft.getY() + pLeft.getPADDLE_HEIGHT()/2);
        ball.setX(pLeft.getX() + pLeft.getPADDLE_WIDTH() + 10);

        assertEquals(true, ball.checkCollisionsPaddleLeft());
    }

    @ParameterizedTest
    @CsvSource({"10, 0", "10, 10", "15, 10", "10, 25", "10, -25"})
    public void ball_collides_with_paddle_right(int xVelocity, int yVelocity) {
        ball.setxVelocity(xVelocity);
        ball.setyVelocity(yVelocity);
        ball.setY(pRight.getY() + pRight.getPADDLE_HEIGHT()/2);
        ball.setX(pRight.getX() - 10);

        assertEquals(true, ball.checkCollisionsPaddleRight());
    }

    @Test
    public void ball_bounced_top_boundary(){
        // TODO
        int final_positionX = 250;
        int final_positionY = 250;
        assertEquals(final_positionX, ball.getX());
        assertEquals(final_positionY, ball.getY());
    }

/*    @ParameterizedTest
    @CsvSource({"10, 50", "50, 100", "100, 30"})
    public void ball_does_not_collide_with_top_boundary(int x, int y) {
        ball.setX(x);
        ball.setY(y);

        assertEquals(false, ball.checkCollisionsTop());
    }*/
}
