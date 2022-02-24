import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class TestBot {

    private final GamePanel gamePanel = new GamePanel();
    private final Player player0 = new Player("Player0", 0);
    private final Player player1 = new Player("Player1", 1);
    private final Paddle paddleLeft = new Paddle(player0);
    private final Paddle paddleRight = new Paddle(player1);
    private final Ball ball = new Ball(paddleLeft, paddleRight, player0, player1, 5);
    private final PaddleMover paddleMover = new PaddleMover(paddleLeft, paddleRight);

    private final Bot bot = new Bot(paddleRight, ball, gamePanel, paddleMover);

    @ParameterizedTest
    @CsvSource({"120, 120", "300, 400", "400, 300", "200, 500"})
    public void move_paddle_closer_to_ball(int paddleRightYPosition, double ballYPosition) {
        paddleRight.setyPosition(paddleRightYPosition);
        ball.setyPosition(ballYPosition);
        double distanceBeforeMove = Math.abs(paddleRight.getyPosition() - ball.getyPosition());
        bot.makeMove();
        double distanceAfterMove = Math.abs(paddleRight.getyPosition() - ball.getyPosition());
        assert(distanceAfterMove <= distanceBeforeMove);
    }

}
