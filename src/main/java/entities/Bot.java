package entities;

import panels.GamePanel;
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

    // Aims to reduce the distance between ball and paddle.
    public void makeMove() {
        double ballCenterPosition = ball.getyCenter();
        int paddleCenterPosition = paddle.getyPosition() + Paddle.PADDLE_HEIGHT/ 2;
        double distancePaddleBall = ballCenterPosition - paddleCenterPosition;
        int key = (distancePaddleBall < 0) ? KeyEvent.VK_UP : KeyEvent.VK_DOWN;

        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, key, 'Z');
        paddleMover.keyPressed(keyEvent);
    }
}
