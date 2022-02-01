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
    @ValueSource(ints={0, 10, 50, 250, 500})
    public void ball_with_different_x_collides_with_top_boundary(int x) {
        ball.setX(x);
        ball.setY(topLim + ball.getBALL_RADIUS());

        assertEquals(true, ball.checkCollisionsTop());
    }

    @ParameterizedTest
    @ValueSource(ints={0, 10, 50, 250, 500})
    public void ball_with_different_x_collides_with_bottom_boundary(int x) {
        ball.setX(x);
        ball.setY(botLim - ball.getBALL_RADIUS());

        assertEquals(true, ball.checkCollisionsBottom());
    }

    @ParameterizedTest
    @ValueSource(ints={0, 10, 50, 250, 500})
    public void ball_with_different_y_collides_with_left_boundary(int y) {
        ball.setX(leftLim + ball.getBALL_RADIUS()); // ball hits boundary with left side
        ball.setY(y);

        assertEquals(true, ball.checkCollisionsLeft());
    }

    @ParameterizedTest
    @ValueSource(ints={0, 10, 50, 250, 500})
    public void ball_with_different_y_collides_with_right_boundary(int y) {
        ball.setX(rightLim - ball.getBALL_RADIUS()); // ball hits boundary with right side
        ball.setY(y);

        assertEquals(true, ball.checkCollisionsRight());
    }

/*    @ParameterizedTest
    @CsvSource({"10, 50", "50, 100", "100, 30"})
    public void ball_does_not_collide_with_top_boundary(int x, int y) {
        ball.setX(x);
        ball.setY(y);

        assertEquals(false, ball.checkCollisionsTop());
    }*/
}
