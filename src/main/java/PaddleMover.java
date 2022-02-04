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
    public int paddleSpeed = 5;


    public void setPaddleVelocity(Paddle p) {
        boolean p_up;
        boolean p_down;
        if (p.getId() == 0){
            p_up = p0_up;
            p_down = p0_down;
        } else {
            p_up = p1_up;
            p_down = p1_down;
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
                //paddle0.setVelocity(-1);
                break;
            case KeyEvent.VK_S:
                p0_down = true;
                //paddle0.setVelocity(1);
                break;
            case KeyEvent.VK_UP:
                p1_up = true;
                //paddle1.setVelocity(-1);
                break;
            case KeyEvent.VK_DOWN:
                p1_down = true;
                //paddle1.setVelocity(1);
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
                break;
            case KeyEvent.VK_S:
                p0_down = false;
                //paddle0.setVelocity(0);
                break;
            case KeyEvent.VK_UP:
                p1_up = false;
                //paddle1.setVelocity(0);
                break;
            case KeyEvent.VK_DOWN:
                p1_down = false;
                //paddle1.setVelocity(0);
                break;
            default:
                break;
        }
    }
}