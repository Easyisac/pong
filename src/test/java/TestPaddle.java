import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPaddle {

    private int x = 50;
    private int y = 50;
    private Paddle p = new Paddle(1,x,y);

    @Test
    public void paddleMoveTest(){
        int velocity = 1;
        p.setVelocity(velocity);
        p.move();

        assertEquals(p.getY(), y+velocity);
    }
}
