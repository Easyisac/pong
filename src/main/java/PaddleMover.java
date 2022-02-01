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
                System.out.println("key pressed: UP arrow");
                paddle.setVelocity(-1);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("key pressed: DOWN arrow");
                paddle.setVelocity(1);
                break;
            default:
                System.out.println("key pressed: not an arrow");
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        System.out.println("key released");
    }
}
