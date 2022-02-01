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
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                paddle.setVelocity(-1);
                break;
            case KeyEvent.VK_DOWN:
                paddle.setVelocity(1);
                break;
            default:
                System.out.println("key pressed: not an arrow");
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                paddle.setVelocity(0);
                break;
            case KeyEvent.VK_DOWN:
                paddle.setVelocity(0);
                break;
            default:
                System.out.println("key relased: not an arrow");
                break;
        }
    }
}
