import java.awt.event.KeyEvent;

public class PaddleMover implements Runnable {

    private final Paddle paddle;

    public PaddleMover(Paddle p) {
        paddle = p;
    }

    @Override
    public void run() {
        while (true) {
            paddle.move();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (paddle.getId() == 0) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> paddle.setVelocity(-1);
                case KeyEvent.VK_S -> paddle.setVelocity(1);
                default -> {
                }
            }
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> paddle.setVelocity(-1);
                case KeyEvent.VK_DOWN -> paddle.setVelocity(1);
                default -> {
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (paddle.getId() == 0) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W, KeyEvent.VK_S -> paddle.setVelocity(0);
                default -> {
                }
            }
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> paddle.setVelocity(0);
                default -> {
                }
            }
        }
    }
}
