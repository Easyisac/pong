import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestPaddle {

    private final int topLimit = GameProperties.GAME_COURT_TOP_LIMIT;
    private final int bottomLimit = GameProperties.GAME_COURT_BOTTOM_LIMIT;
    private final int leftLimit = GameProperties.GAME_COURT_LEFT_LIMIT;
    private final int rightLimit = GameProperties.GAME_COURT_RIGHT_LIMIT;

    private final Player playerRight = new Player("Player1", 1);

    private final Paddle paddle = new Paddle(playerRight);
    private final int paddle_height = paddle.getPADDLE_HEIGHT();

    private final int yStart = paddle.getyPosition();

    @ParameterizedTest
    @ValueSource(ints={1,2,3,4,-4,20,-30})
    public void paddle_shift_position_after_move_command(int velocity){
        paddle.setVelocity(velocity);
        paddle.move();

        assertEquals(yStart + velocity, paddle.getyPosition());
    }

    @ParameterizedTest
    @ValueSource(ints={-451, -500, -550, -600})
    public void paddle_stops_when_hitting_top_boundary(int velocity){
        paddle.setVelocity(velocity);
        paddle.move();

        assertEquals(topLimit, paddle.getyPosition());
    }

    @ParameterizedTest
    @ValueSource(ints={451, 500, 550, 600})
    public void paddle_stops_when_hitting_bot_boundary(int velocity){
        paddle.setVelocity(velocity);
        paddle.move();

        assertEquals(bottomLimit - paddle_height, paddle.getyPosition());
    }
}
