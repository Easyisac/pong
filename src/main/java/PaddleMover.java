import java.awt.event.KeyEvent;

public class PaddleMover implements Runnable {

    private final Paddle paddle0;
    private final Paddle paddle1;

    public PaddleMover(Paddle p0, Paddle p1) {
        paddle0 = p0;
        paddle1 = p1;
    }

    @Override
    public void run() {
        while (true) {
            paddle0.move();
            paddle1.move();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                paddle0.setVelocity(-1);
                break;
            case KeyEvent.VK_S:
                paddle0.setVelocity(1);
                break;
            case KeyEvent.VK_UP:
                paddle1.setVelocity(-1);
                break;
            case KeyEvent.VK_DOWN:
                paddle1.setVelocity(1);
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                paddle0.setVelocity(0);
                break;
            case KeyEvent.VK_S:
                paddle0.setVelocity(0);
                break;
            case KeyEvent.VK_UP:
                paddle1.setVelocity(0);
                break;
            case KeyEvent.VK_DOWN:
                paddle1.setVelocity(0);
                break;
            default:
                break;
        }
    }
}
