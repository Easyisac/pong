import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.event.KeyEvent;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPaddleMover {

    private final int topLim = 0;
    private final int botLim = 500;
    private final int leftLim = 0;
    private final int rightLim = 500;

    private final Paddle p = new Paddle(1, topLim, botLim, leftLim, rightLim);
    private final PaddleMover pm = new PaddleMover(p);
    private final GamePanel gp = new GamePanel();

    private final int yStart = p.getY();

    @ParameterizedTest
    @MethodSource("provideParametersPressed")
    public void paddle_mover_key_pressed_test(int key, int result) {
        KeyEvent ke = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, 'Z');
        pm.keyPressed(ke);
        assertEquals(result, p.getVelocity());
    }

    private static Stream<Arguments> provideParametersPressed() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, -1),
                Arguments.of(KeyEvent.VK_DOWN, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersReleased")
    public void paddle_mover_key_released_test(int key, int result) {
        KeyEvent ke = new KeyEvent(gp, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, key, 'Z');
        pm.keyReleased(ke);
        assertEquals(result, p.getVelocity());
    }

    private static Stream<Arguments> provideParametersReleased() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, 0),
                Arguments.of(KeyEvent.VK_DOWN, 0)
        );
    }
}
