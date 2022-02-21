import java.awt.event.KeyEvent;

public class Bot {

    private Paddle paddle;
    private Ball ball;
    private GamePanel gamePanel;
    private PaddleMover paddleMover;

    public Bot(Paddle paddle, Ball ball, GamePanel gamePanel, PaddleMover paddleMover) {
        this.paddle = paddle;
        this.ball = ball;
        this.gamePanel = gamePanel;
        this.paddleMover = paddleMover;
    }

    public void move() {

        double ballPosition = ball.getY();
        int paddlePosition = paddle.getY() + paddle.getPADDLE_HEIGHT()/2;
        double distancePaddleBall = ballPosition - paddlePosition;
        int key = (distancePaddleBall<0) ?  KeyEvent.VK_UP : KeyEvent.VK_DOWN;

        KeyEvent k = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, 'Z');
        paddleMover.keyPressed(k);
    }


}
