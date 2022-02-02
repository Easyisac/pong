import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    private static PaddleDrawer pd0;
    private static PaddleDrawer pd1;
    private static BallDrawer b;

    private static PaddleMover pm;

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
        //this.addKeyListener(new GameKeyListener());
    }

    //Standard method, draws static images, calls draw function
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(1));
        g2.drawRect(leftBorder, topBorder, gameWidth, gameHeight);
        /* Test sulle posizioni delle barrette e della palla
        g2.drawLine(100,50,100,550); //righe verticali per le barrette
        g2.drawLine(500,50,500,550);
        g2.drawLine(110,50,110,550);
        g2.drawLine(490,50,490,550);
        g2.drawLine(50,50,550,550); //linee diagonali per la palla
        g2.drawLine(550,50,50,550);*/
        draw(g2);
        repaint();
    }

    //Method that calls drawing functions for objects
    public void draw(Graphics2D g2){
        pd0.draw(g2);
        pd1.draw(g2);
        b.draw(g2);
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


    public static void main(String[] args) {
        GamePanel panel = new GamePanel();

        JFrame jf = new JFrame();
        jf.setTitle("PONG");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(panel);
        jf.pack();
        jf.setResizable(false);
        jf.addKeyListener(new GameKeyListener());
        Paddle p0 = new Paddle(0, panel.topBorder, panel.gameHeight + panel.topBorder, panel.leftBorder, panel.gameWidth + panel.leftBorder);
        Paddle p1 = new Paddle(1, panel.topBorder, panel.gameHeight + panel.topBorder, panel.leftBorder, panel.gameWidth + panel.leftBorder);
        Ball ball = new Ball(panel.topBorder, panel.gameHeight + panel.topBorder, panel.leftBorder, panel.gameWidth + panel.leftBorder, p0, p1);
        pm = new PaddleMover(p0,p1);
        pd0 = new PaddleDrawer(p0);
        pd1 = new PaddleDrawer(p1);
        b = new BallDrawer(ball);

        Thread pt0 = new Thread(pm);

        pt0.start();
    }
}
