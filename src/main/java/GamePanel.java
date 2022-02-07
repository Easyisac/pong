import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    private static Player pl0;
    private static Player pl1;
    private static Paddle p0;
    private static Paddle p1;
    private static Ball ball;
    private static PaddleDrawer pd0;
    private static PaddleDrawer pd1;
    private static BallDrawer bd;
    private static PlayerDrawer pld0;
    private static PlayerDrawer pld1;

    private static PaddleMover pm;

    public int gameHeight = 500;
    public int gameWidth = 500;
    public int topBorder = 100;
    public int botBorder = 50;
    public int leftBorder = 50;
    public int rightBorder = 50;
    private Dimension panelSize = new Dimension(gameWidth + leftBorder + rightBorder,
            gameHeight + topBorder + botBorder);

    public GamePanel(){
        setPreferredSize(panelSize);
        setBackground(Color.black);
        addKeyListener(new GameKeyListener());
        setFocusable(true);
        //setDoubleBuffered(true);
        pl0 = new Player("Player0", 0);
        pl1 = new Player("Player1", 1);
        p0 = new Paddle(pl0, topBorder, gameHeight + topBorder, leftBorder, gameWidth + leftBorder);
        p1 = new Paddle(pl1, topBorder, gameHeight + topBorder, leftBorder, gameWidth + leftBorder);
        ball = new Ball(topBorder, gameHeight + topBorder, leftBorder, gameWidth + leftBorder, p0, p1);
        pm = new PaddleMover(p0,p1);
        pd0 = new PaddleDrawer(p0);
        pd1 = new PaddleDrawer(p1);
        bd = new BallDrawer(ball);
        pld0 = new PlayerDrawer(pl0, topBorder, leftBorder, rightBorder, gameWidth);
        pld1 = new PlayerDrawer(pl1, topBorder, leftBorder, rightBorder, gameWidth);
    }

    //Standard method, draws static images, calls draw function
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(1));
        g2.drawRect(leftBorder, topBorder, gameWidth, gameHeight);
        // Test sulle posizioni delle barrette e della palla
        /*g2.drawLine(100,50,100,550); //righe verticali per le barrette
        g2.drawLine(500,50,500,550);
        g2.drawLine(110,50,110,550);
        g2.drawLine(490,50,490,550);
        g2.drawLine(50,50,550,550); //linee diagonali per la palla
        g2.drawLine(550,50,50,550);*/
        draw(g2);
    }

    //Method that calls drawing functions for objects
    public void draw(Graphics2D g2){
        pd0.draw(g2);
        pd1.draw(g2);
        bd.draw(g2);
        pld0.draw(g2);
        pld1.draw(g2);
    }

    @Override
    public void run() {
        while (true) {
            p0.move();
            p1.move();
            ball.move();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class GameKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            pm.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            pm.keyReleased(e);
        }
    }
}
