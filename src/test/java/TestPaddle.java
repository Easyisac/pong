import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestPaddle {

    private final int topLim = 0;
    private final int botLim = 500;
    private final int leftLim = 0;
    private final int rightLim = 500;

    private final Player pl1 = new Player("Player1", 1);

    private final Paddle p = new Paddle(pl1, topLim, botLim, leftLim, rightLim);
    private final int paddle_height = p.getPADDLE_HEIGHT();

    private final int yStart = p.getY();

    @ParameterizedTest
    @ValueSource(ints={1,2,3,4,-4,20,-30})
    public void paddle_inside_panel_move_test(int velocity){
        p.setVelocity(velocity);
        p.move();

        assertEquals(yStart + velocity, p.getY());
    }

    @ParameterizedTest
    @ValueSource(ints={-451, -500, -550, -600})
    public void paddle_outside_top_panel_move_test(int velocity){
        p.setVelocity(velocity);
        p.move();

        assertEquals(topLim, p.getY());
    }

    @ParameterizedTest
    @ValueSource(ints={451, 500, 550, 600})
    public void paddle_outside_bot_panel_move_test(int velocity){
        p.setVelocity(velocity);
        p.move();

        assertEquals(botLim - paddle_height, p.getY());
    }
}
