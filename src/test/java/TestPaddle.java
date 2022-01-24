import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestPaddle {

    private int x = 50;
    private int y = 50;
    private Paddle p = new Paddle(1,x,y);

    @ParameterizedTest
    @ValueSource(ints={1,2,3,4,-4,20,-50})
    public void paddleMoveTest(int velocity){
        p.setVelocity(velocity);
        p.move();

        assertEquals(p.getY(), y+velocity);
    }
}
