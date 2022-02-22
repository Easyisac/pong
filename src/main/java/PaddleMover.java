import java.awt.event.KeyEvent;
public class PaddleMover {

    private final Paddle paddleLeft;
    private final Paddle paddleRight;

    private boolean paddleLeftUpFlag;
    private boolean paddleLeftDownFlag;
    private boolean paddleRightUpFlag;
    private boolean paddleRightDownFlag;

    private double paddle0Speed = 5;
    private double paddle1Speed = 5;

    public PaddleMover(Paddle paddleLeft, Paddle paddleRight) {
        this.paddleLeft = paddleLeft;
        this.paddleRight = paddleRight;
    }

    public double getPaddle0Speed() {
        return paddle0Speed;
    }

    public void setPaddle0Speed(double paddle0Speed) {
        this.paddle0Speed = paddle0Speed;
    }

    public double getPaddle1Speed() {
        return paddle1Speed;
    }

    public void setPaddle1Speed(double paddle1Speed) {
        this.paddle1Speed = paddle1Speed;
    }

    private void setPaddleVelocity(Paddle paddle) {
        boolean p_up;
        boolean p_down;
        double paddleSpeed;
        if (paddle.getPlayer().getId() == 0){
            p_up = paddleLeftUpFlag;
            p_down = paddleLeftDownFlag;
            paddleSpeed = paddle0Speed;
        } else {
            p_up = paddleRightUpFlag;
            p_down = paddleRightDownFlag;
            paddleSpeed = paddle1Speed;
        }
        if (!p_up && !p_down) {
            paddle.setVelocity(0);
        } else if (p_up) {
            paddle.setVelocity(-paddleSpeed);
        } else if (p_down) {
            paddle.setVelocity(paddleSpeed);
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                paddleLeftUpFlag = true;
                paddleLeftDownFlag = false;
                setPaddleVelocity(paddleLeft);
                break;
            case KeyEvent.VK_S:
                paddleLeftDownFlag = true;
                paddleLeftUpFlag = false;
                setPaddleVelocity(paddleLeft);
                break;
            case KeyEvent.VK_UP:
                paddleRightUpFlag = true;
                paddleRightDownFlag = false;
                setPaddleVelocity(paddleRight);
                break;
            case KeyEvent.VK_DOWN:
                paddleRightDownFlag = true;
                paddleRightUpFlag = false;
                setPaddleVelocity(paddleRight);
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                paddleLeftUpFlag = false;
                setPaddleVelocity(paddleLeft);
                break;
            case KeyEvent.VK_S:
                paddleLeftDownFlag = false;
                setPaddleVelocity(paddleLeft);
                break;
            case KeyEvent.VK_UP:
                paddleRightUpFlag = false;
                setPaddleVelocity(paddleRight);
                break;
            case KeyEvent.VK_DOWN:
                paddleRightDownFlag = false;
                setPaddleVelocity(paddleRight);
                break;
            default:
                break;
        }
    }
}