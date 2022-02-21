import java.awt.event.KeyEvent;
public class PaddleMover{

    private final Paddle paddle0;
    private final Paddle paddle1;
    public PaddleMover(Paddle p0, Paddle p1) {
        paddle0 = p0;
        paddle1 = p1;
    }

    private boolean p0_up;
    private boolean p0_down;
    private boolean p1_up;
    private boolean p1_down;
    public static int speed = 5;
    private double paddle0Speed = speed;
    private double paddle1Speed = speed;

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

    private void setPaddleVelocity(Paddle p) {
        boolean p_up;
        boolean p_down;
        double paddleSpeed;
        if (p.getPlayer().getId() == 0){
            p_up = p0_up;
            p_down = p0_down;
            paddleSpeed = paddle0Speed;
        } else {
            p_up = p1_up;
            p_down = p1_down;
            paddleSpeed = paddle1Speed;
        }
        if (!p_up && !p_down) {
            p.setVelocity(0);
        } else if (p_up) {
            p.setVelocity(-paddleSpeed);
        } else if (p_down) {
            p.setVelocity(paddleSpeed);
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                p0_up = true;
                p0_down = false;
                setPaddleVelocity(paddle0);
                break;
            case KeyEvent.VK_S:
                p0_down = true;
                p0_up = false;
                setPaddleVelocity(paddle0);
                break;
            case KeyEvent.VK_UP:
                p1_up = true;
                p1_down = false;
                setPaddleVelocity(paddle1);
                break;
            case KeyEvent.VK_DOWN:
                p1_down = true;
                p1_up = false;
                setPaddleVelocity(paddle1);
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                p0_up = false;
                //paddle0.setVelocity(0);
                setPaddleVelocity(paddle0);
                break;
            case KeyEvent.VK_S:
                p0_down = false;
                //paddle0.setVelocity(0);
                setPaddleVelocity(paddle0);
                break;
            case KeyEvent.VK_UP:
                p1_up = false;
                //paddle1.setVelocity(0);
                setPaddleVelocity(paddle1);
                break;
            case KeyEvent.VK_DOWN:
                p1_down = false;
                //paddle1.setVelocity(0);
                setPaddleVelocity(paddle1);
                break;
            default:
                break;
        }
    }
}