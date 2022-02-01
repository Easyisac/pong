import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static PaddleDrawer pd1;
    private static PaddleDrawer pd2;
    private static BallDrawer b;

    public int gameHeight = 500;
    public int gameWidth = 500;
    public int topBorder = 50;
    public int botBorder = 50;
    public int leftBorder = 50;
    public int rightBorder = 50;
    private Dimension panelSize = new Dimension(gameWidth + leftBorder + rightBorder,
            gameHeight + topBorder + botBorder);

    public GamePanel(){
        this.setPreferredSize(panelSize);
        this.setBackground(Color.black);
    }

    //Standard method, draws static images, calls draw function
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(1));
        g2.drawRect(leftBorder, topBorder, gameWidth, gameHeight);
        draw(g2);
        repaint();
    }

    //Method that calls drawing functions for objects
    public void draw(Graphics2D g2){
        pd1.draw(g2);
        pd2.draw(g2);
        b.draw(g2);
    }




    public static void main(String[] args) throws InterruptedException {
        GamePanel panel = new GamePanel();

        JFrame jf = new JFrame();
        jf.setTitle("PONG");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(panel);
        jf.pack();
        Paddle p1 = new Paddle(0, 100, 100, panel.topBorder, panel.gameHeight + panel.topBorder);
        Paddle p2 = new Paddle(1, 500-p1.getPADDLE_WIDTH(), 300, panel.topBorder, panel.gameHeight + panel.topBorder);
        Ball ball = new Ball(panel.topBorder, panel.gameHeight + panel.topBorder, panel.leftBorder, panel.gameWidth + panel.leftBorder);
        pd1 = new PaddleDrawer(p1);
        pd2 = new PaddleDrawer(p2);
        b = new BallDrawer(ball);
    }
}
