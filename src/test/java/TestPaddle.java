import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestPaddle {

    private final int topLim = GameProperties.GAME_COURT_TOP_LIMIT;
    private final int botLim = GameProperties.BOTTOM_LIMIT;
    private final int leftLim = GameProperties.LEFT_LIMIT;
    private final int rightLim = GameProperties.RIGHT_LIMIT;

    private final Player pl1 = new Player("Player1", 1);

    private final Paddle p = new Paddle(pl1);
    private final int paddle_height = p.getPADDLE_HEIGHT();

    private final int yStart = p.getyPosition();

    @ParameterizedTest
    @ValueSource(ints={1,2,3,4,-4,20,-30})
    public void paddle_inside_panel_move_test(int velocity){
        p.setVelocity(velocity);
        p.move();

        assertEquals(yStart + velocity, p.getyPosition());
    }

    @ParameterizedTest
    @ValueSource(ints={-451, -500, -550, -600})
    public void paddle_outside_top_panel_move_test(int velocity){
        p.setVelocity(velocity);
        p.move();

        assertEquals(topLim, p.getyPosition());
    }

    @ParameterizedTest
    @ValueSource(ints={451, 500, 550, 600})
    public void paddle_outside_bot_panel_move_test(int velocity){
        p.setVelocity(velocity);
        p.move();

        assertEquals(botLim - paddle_height, p.getyPosition());
    }
}
