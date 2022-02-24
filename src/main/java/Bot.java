import java.awt.event.KeyEvent;

public class Bot {

    private final Paddle paddle;
    private final Ball ball;
    private final GamePanel gamePanel;
    private final PaddleMover paddleMover;

    public Bot(Paddle paddle, Ball ball, GamePanel gamePanel, PaddleMover paddleMover) {
        this.paddle = paddle;
        this.ball = ball;
        this.gamePanel = gamePanel;
        this.paddleMover = paddleMover;
    }

    public void makeMove() {

        double ballPosition = ball.getyPosition();
        int paddlePosition = paddle.getyPosition() + paddle.getPADDLE_HEIGHT()/2;
        double distancePaddleBall = ballPosition - paddlePosition;
        int key = (distancePaddleBall<0) ?  KeyEvent.VK_UP : KeyEvent.VK_DOWN;

        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, 'Z');
        paddleMover.keyPressed(keyEvent);
    }


}
