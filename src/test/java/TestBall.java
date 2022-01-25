import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBall {

    private int topLim = 0;
    private int botLim = 500;
    private int leftLim = 0;
    private int rightLim = 500;


    private Ball ball = new Ball(topLim, botLim, leftLim, rightLim);
    private final int x_start = ball.getX();
    private final int y_start = ball.getY();


    @ParameterizedTest
    @CsvSource({"1, 1", "5, 5", "-10,20", "25, -30"})
    public void ball_inside_panel_move_test(int xVelocity, int yVelocity) {
        ball.setxVelocity(xVelocity);
        ball.setyVelocity(yVelocity);
        ball.move();

        assertEquals(x_start+xVelocity, ball.getX());
        assertEquals(y_start+yVelocity, ball.getY());
    }
}
