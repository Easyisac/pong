import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestPaddle {

    private int x = 50;
    private int y = 50;
    private int topLimY = 0;
    private int botLimY = 500;
    private Paddle p = new Paddle(1,x,y, topLimY, botLimY);
    private int paddle_height = p.getPaddleHeight();


    @ParameterizedTest
    @ValueSource(ints={1,2,3,4,-4,20,-30})
    public void paddle_inside_panel_move_test(int velocity){
        p.setVelocity(velocity);
        p.move();

        assertEquals(p.getY(), y+velocity);
    }

    @ParameterizedTest
    @ValueSource(ints={-50,-130, -110, -200})
    public void paddle_outside_top_panel_move_test(int velocity){
        p.setVelocity(velocity);
        p.move();

        assertEquals(p.getY(), topLimY);
    }

    @ParameterizedTest
    @ValueSource(ints={451, 500, 550, 600})
    public void paddle_outside_bot_panel_move_test(int velocity){
        p.setVelocity(velocity);
        p.move();

        assertEquals(p.getY(), botLimY-paddle_height);
    }
}
