import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.event.KeyEvent;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPaddleMover {

    private static final int topLim = 0;
    private static final int botLim = 500;
    private static final int leftLim = 0;
    private static final int rightLim = 500;

    private static final Paddle p0 = new Paddle(0, topLim, botLim, leftLim, rightLim);
    private static final Paddle p1 = new Paddle(1, topLim, botLim, leftLim, rightLim);
    private static final PaddleMover pm = new PaddleMover(p0, p1);
    private final GamePanel gp = new GamePanel();

    @ParameterizedTest
    @MethodSource("provideParametersPressed")
    public void paddle_mover_key_pressed_test(int key, int result) {
        KeyEvent ke = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, 'Z');
        pm.keyPressed(ke);
        pm.setPaddleVelocity(p1);
        assertEquals(result, p1.getVelocity());
    }

    private static Stream<Arguments> provideParametersPressed() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, -pm.paddleSpeed),
                Arguments.of(KeyEvent.VK_DOWN, pm.paddleSpeed)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersReleased")
    public void paddle_mover_key_released_test(int key, int result) {
        KeyEvent ke = new KeyEvent(gp, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, key, 'Z');
        p1.setVelocity(1);
        pm.keyReleased(ke);
        pm.setPaddleVelocity(p1);
        assertEquals(result, p1.getVelocity());
    }

    private static Stream<Arguments> provideParametersReleased() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, 0),
                Arguments.of(KeyEvent.VK_DOWN, 0)
        );
    }


    @ParameterizedTest
    @MethodSource("provideParametersPressedContemporary")
    public void paddle_mover_key_pressed_contemporary_test(int key, int[] result) {
        KeyEvent ke = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, 'Z');
        pm.keyPressed(ke);
        pm.keyPressed(ke);
        pm.setPaddleVelocity(p0);
        pm.setPaddleVelocity(p1);
        assertEquals(result[0], p0.getVelocity());
        assertEquals(result[1], p1.getVelocity());
    }

    private static Stream<Arguments> provideParametersPressedContemporary() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, new int[]{0,-pm.paddleSpeed}),
                Arguments.of(KeyEvent.VK_DOWN, new int[]{0,pm.paddleSpeed}),
                Arguments.of(KeyEvent.VK_W, new int[]{-pm.paddleSpeed,0}),
                Arguments.of(KeyEvent.VK_S, new int[]{pm.paddleSpeed,0})
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersReleasedContemporary")
    public void paddle_mover_key_released_contemporary_test(int key, int[] result) {
        KeyEvent ke = new KeyEvent(gp, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, key, 'Z');
        p0.setVelocity(1);
        p1.setVelocity(1);
        pm.keyReleased(ke);
        pm.keyReleased(ke);
        pm.setPaddleVelocity(p0);
        pm.setPaddleVelocity(p1);
        assertEquals(result[0], p0.getVelocity());
        assertEquals(result[1], p1.getVelocity());
    }

    private static Stream<Arguments> provideParametersReleasedContemporary() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, new int[]{1,0}),
                Arguments.of(KeyEvent.VK_DOWN, new int[]{1,0}),
                Arguments.of(KeyEvent.VK_W, new int[]{0,1}),
                Arguments.of(KeyEvent.VK_S, new int[]{0,1})
        );
    }

}
