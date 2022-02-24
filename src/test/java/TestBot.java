import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class TestBot {

    private final GamePanel gamePanel = new GamePanel();
    private final Player playerLeft = new Player("Player0", 0);
    private final Player playerRight = new Player("Player1", 1);
    private final Paddle paddleLeft = new Paddle(playerLeft);
    private final Paddle paddleRight = new Paddle(playerRight);
    private final Ball ball = new Ball(paddleLeft, paddleRight, playerLeft, playerRight, 5);
    private final PaddleMover paddleMover = new PaddleMover(paddleLeft, paddleRight);

    private final Bot bot = new Bot(paddleRight, ball, gamePanel, paddleMover);

    @ParameterizedTest
    @CsvSource({"120, 120", "300, 400", "400, 300", "200, 500"})
    public void bot_moves_paddle_closer_to_ball_position(int paddleRightYPosition, double ballYPosition) {
        paddleRight.setyPosition(paddleRightYPosition);
        ball.setyPosition(ballYPosition);
        double distanceBeforeMove = Math.abs(paddleRight.getyPosition() - ball.getyPosition());
        bot.makeMove();
        double distanceAfterMove = Math.abs(paddleRight.getyPosition() - ball.getyPosition());
        assert(distanceAfterMove <= distanceBeforeMove);
    }

}
