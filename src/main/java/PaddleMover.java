import java.awt.event.KeyEvent;

public class PaddleMover implements Runnable {

    private final Paddle paddle;

    public PaddleMover(Paddle p) {
        paddle = p;
    }

    @Override
    public void run() {
    }

    public void keyPressed(KeyEvent e) {
        if (paddle.getId() == 0) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    paddle.setVelocity(-1);
                    break;
                case KeyEvent.VK_S:
                    paddle.setVelocity(1);
                    break;
                default:
                    break;
            }
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    paddle.setVelocity(-1);
                    break;
                case KeyEvent.VK_DOWN:
                    paddle.setVelocity(1);
                    break;
                default:
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (paddle.getId() == 0) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    paddle.setVelocity(0);
                    break;
                case KeyEvent.VK_S:
                    paddle.setVelocity(0);
                    break;
                default:
                    break;
            }
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    paddle.setVelocity(0);
                    break;
                case KeyEvent.VK_DOWN:
                    paddle.setVelocity(0);
                    break;
                default:
                    break;
            }
        }
    }
}
