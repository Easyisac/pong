import static org.junit.jupiter.api.Assertions.assertEquals;

import entities.Paddle;
import entities.Player;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import panels.GamePanel;

public class TestPaddle {

    private final Player playerRight = new Player("Player1", 1);

    private final Paddle paddle = new Paddle(playerRight);

    private final int yStart = paddle.getyPosition();

    @ParameterizedTest
    @ValueSource(ints={1,2,3,4,-4,20,-30})
    public void paddle_moves_within_boundaries(int velocity){
        paddle.setSpeed(velocity);
        paddle.move();

        assertEquals(yStart + velocity, paddle.getyPosition());
    }

    @ParameterizedTest
    @ValueSource(ints={-451, -500, -550, -600})
    public void paddle_stops_when_hitting_top_boundary(int velocity){
        paddle.setSpeed(velocity);
        paddle.move();

        assertEquals(GamePanel.GAME_COURT_TOP_LIMIT, paddle.getyPosition());
    }

    @ParameterizedTest
    @ValueSource(ints={451, 500, 550, 600})
    public void paddle_stops_when_hitting_bot_boundary(int velocity){
        paddle.setSpeed(velocity);
        paddle.move();

        assertEquals(GamePanel.GAME_COURT_BOTTOM_LIMIT - Paddle.PADDLE_HEIGHT, paddle.getyPosition());
    }
}
