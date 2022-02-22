import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.event.KeyEvent;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPaddleMover {

    private final Player pl0 = new Player("Player0", 0);
    private final Player pl1 = new Player("Player1", 1);

    private final Paddle p0 = new Paddle(pl0);
    private final Paddle p1 = new Paddle(pl1);
    private final GamePanel gp = new GamePanel();
    private final PaddleMover pm = new PaddleMover(p0, p1);

    @ParameterizedTest
    @MethodSource("provideParametersPressed")
    public void paddle_mover_key_pressed_test(int key, double result) {
        PaddleMover pm = new PaddleMover(p0, p1);
        KeyEvent ke = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, 'Z');
        pm.keyPressed(ke);
        assertEquals(result, p1.getVelocity());
    }

    private Stream<Arguments> provideParametersPressed() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, -pm.getPaddle1Speed()),
                Arguments.of(KeyEvent.VK_DOWN, pm.getPaddle1Speed())
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersReleased")
    public void paddle_mover_key_released_test(int key, double result) {
        PaddleMover pm = new PaddleMover(p0, p1);
        KeyEvent ke = new KeyEvent(gp, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, key, 'Z');
        p1.setVelocity(pm.getPaddle1Speed());
        pm.keyReleased(ke);
        assertEquals(result, p1.getVelocity());
    }

    private Stream<Arguments> provideParametersReleased() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, 0),
                Arguments.of(KeyEvent.VK_DOWN, 0)
        );
    }


    @ParameterizedTest
    @MethodSource("provideParametersPressedContemporary")
    public void paddle_mover_key_pressed_contemporary_test(int key, double[] result) {
        PaddleMover pm = new PaddleMover(p0, p1);
        p0.setVelocity(0);
        p1.setVelocity(0);
        KeyEvent ke = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, 'Z');
        pm.keyPressed(ke);
        assertEquals(result[0], p0.getVelocity());
        assertEquals(result[1], p1.getVelocity());
    }

    private Stream<Arguments> provideParametersPressedContemporary() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, new double[]{0,-pm.getPaddle1Speed()}),
                Arguments.of(KeyEvent.VK_DOWN, new double[]{0, pm.getPaddle1Speed()}),
                Arguments.of(KeyEvent.VK_W, new double[]{-pm.getPaddle0Speed(),0}),
                Arguments.of(KeyEvent.VK_S, new double[]{pm.getPaddle0Speed(),0})
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersReleasedContemporary")
    public void paddle_mover_key_released_contemporary_test(int key, double[] result) {
        PaddleMover pm = new PaddleMover(p0, p1);
        p0.setVelocity(0);
        p1.setVelocity(0);
        KeyEvent ke = new KeyEvent(gp, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, key, 'Z');
        p0.setVelocity(pm.getPaddle0Speed());
        p1.setVelocity(pm.getPaddle1Speed());
        pm.keyReleased(ke);
        assertEquals(result[0], p0.getVelocity());
        assertEquals(result[1], p1.getVelocity());
    }

    private Stream<Arguments> provideParametersReleasedContemporary() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, new double[]{pm.getPaddle1Speed(),0}),
                Arguments.of(KeyEvent.VK_DOWN, new double[]{pm.getPaddle1Speed(),0}),
                Arguments.of(KeyEvent.VK_W, new double[]{0, pm.getPaddle0Speed()}),
                Arguments.of(KeyEvent.VK_S, new double[]{0, pm.getPaddle0Speed()})
        );
    }

}
