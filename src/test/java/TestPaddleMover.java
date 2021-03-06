import entities.Paddle;
import entities.PaddleMover;
import entities.Player;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import panels.GamePanel;

import java.awt.event.KeyEvent;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPaddleMover {

    private final Player playerLeft = new Player("Player1", 0);
    private final Player playerRight = new Player("Player2", 1);

    private final Paddle paddleLeft = new Paddle(playerLeft);
    private final Paddle paddleRight = new Paddle(playerRight);
    private final GamePanel gamePanel = new GamePanel();
    private final PaddleMover paddleMover = new PaddleMover(paddleLeft, paddleRight);

    @ParameterizedTest
    @MethodSource("provideParametersPressed")
    public void paddle_velocity_changes_when_key_is_pressed(int key, double result) {
        PaddleMover pm = new PaddleMover(paddleLeft, paddleRight);
        KeyEvent ke = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, 'Z');
        pm.keyPressed(ke);
        assertEquals(result, paddleRight.getSpeed());
    }

    private Stream<Arguments> provideParametersPressed() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, -paddleMover.getPaddleRightSpeed()),
                Arguments.of(KeyEvent.VK_DOWN, paddleMover.getPaddleRightSpeed())
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersReleased")
    public void paddle_velocity_to_zero_when_releasing_key(int key, double result) {
        PaddleMover pm = new PaddleMover(paddleLeft, paddleRight);
        KeyEvent ke = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, key, 'Z');
        paddleRight.setSpeed(pm.getPaddleRightSpeed());
        pm.keyReleased(ke);
        assertEquals(result, paddleRight.getSpeed());
    }

    private Stream<Arguments> provideParametersReleased() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, 0),
                Arguments.of(KeyEvent.VK_DOWN, 0)
        );
    }


    @ParameterizedTest
    @MethodSource("provideParametersPressedContemporary")
    public void on_key_pressed_velocity_changes_only_on_corresponding_paddle(int key, double[] result) {
        PaddleMover pm = new PaddleMover(paddleLeft, paddleRight);
        paddleLeft.setSpeed(0);
        paddleRight.setSpeed(0);
        KeyEvent ke = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, 'Z');
        pm.keyPressed(ke);
        assertEquals(result[0], paddleLeft.getSpeed());
        assertEquals(result[1], paddleRight.getSpeed());
    }

    private Stream<Arguments> provideParametersPressedContemporary() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, new double[]{0,-paddleMover.getPaddleRightSpeed()}),
                Arguments.of(KeyEvent.VK_DOWN, new double[]{0, paddleMover.getPaddleRightSpeed()}),
                Arguments.of(KeyEvent.VK_W, new double[]{-paddleMover.getPaddleLeftSpeed(),0}),
                Arguments.of(KeyEvent.VK_S, new double[]{paddleMover.getPaddleLeftSpeed(),0})
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersReleasedContemporary")
    public void on_key_released_velocity_goes_to_zero_only_on_corresponding_paddle(int key, double[] result) {
        PaddleMover pm = new PaddleMover(paddleLeft, paddleRight);
        paddleLeft.setSpeed(0);
        paddleRight.setSpeed(0);
        KeyEvent ke = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, key, 'Z');
        paddleLeft.setSpeed(pm.getPaddleLeftSpeed());
        paddleRight.setSpeed(pm.getPaddleRightSpeed());
        pm.keyReleased(ke);
        assertEquals(result[0], paddleLeft.getSpeed());
        assertEquals(result[1], paddleRight.getSpeed());
    }

    private Stream<Arguments> provideParametersReleasedContemporary() {
        return Stream.of(
                Arguments.of(KeyEvent.VK_UP, new double[]{paddleMover.getPaddleRightSpeed(),0}),
                Arguments.of(KeyEvent.VK_DOWN, new double[]{paddleMover.getPaddleRightSpeed(),0}),
                Arguments.of(KeyEvent.VK_W, new double[]{0, paddleMover.getPaddleLeftSpeed()}),
                Arguments.of(KeyEvent.VK_S, new double[]{0, paddleMover.getPaddleLeftSpeed()})
        );
    }

}
