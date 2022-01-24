import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestPaddle {

    private int x = 50;
    private int y = 50;
    private Paddle p = new Paddle(1,x,y);

    private int topLimY = 0;
    private int botLimY = 100;

    @ParameterizedTest
    @ValueSource(ints={1,2,3,4,-4,20,-30})
    public void paddle_inside_panel_move_test(int velocity){
        p.setVelocity(velocity);
        p.move(topLimY,botLimY);

        assertEquals(p.getY(), y+velocity);
    }

    @ParameterizedTest
    @ValueSource(ints={-50,-130, -110, -200})
    public void paddle_outside_top_panel_move_test(int velocity){
        p.setVelocity(velocity);
        p.move(topLimY,botLimY);

        assertEquals(p.getY(), topLimY);
    }

    @ParameterizedTest
    @ValueSource(ints={50, 130, 110, 200})
    public void paddle_outside_bot_panel_move_test(int velocity){
        p.setVelocity(velocity);
        p.move(topLimY,botLimY);

        assertEquals(p.getY(), botLimY);
    }
}
