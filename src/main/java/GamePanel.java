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

    private int velModule = 3;
    private int maxScore = 10;
    private static boolean pause = false;

    private static Bot bot;

    public GamePanel(){
        setPreferredSize(panelSize);
        setBackground(Color.black);
        addKeyListener(new GameKeyListener());
        setFocusable(true);
    }

    public void startGame(String sName0, String sName1){
        pl0 = new Player(sName0, 0);
        pl1 = new Player(sName1, 1);
        p0 = new Paddle(pl0, topBorder, gameHeight + topBorder, leftBorder, gameWidth + leftBorder);
        p1 = new Paddle(pl1, topBorder, gameHeight + topBorder, leftBorder, gameWidth + leftBorder);
        ball = new Ball(topBorder, gameHeight + topBorder, leftBorder, gameWidth + leftBorder,
                p0, p1, pl0, pl1, velModule);
        pm = new PaddleMover(p0,p1);
        pd0 = new PaddleDrawer(p0);
        pd1 = new PaddleDrawer(p1);
        bd = new BallDrawer(ball);
        pld0 = new PlayerDrawer(pl0, topBorder, leftBorder, rightBorder, gameWidth);
        pld1 = new PlayerDrawer(pl1, topBorder, leftBorder, rightBorder, gameWidth);

        bot = new Bot(p1, ball, this, pm);

        Thread game = new Thread(this);
        game.start();
    }

    public int getVelModule() {
        return velModule;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setVelModule(int velModule) {
        this.velModule = velModule;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    //Standard method, draws static images, calls draw function
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(1));
        g2.drawRect(leftBorder, topBorder, gameWidth, gameHeight);
        if (pause){
            String pauseString = "PAUSE";
            g2.setFont(new Font("Arial", Font.PLAIN, 40));
            int stringWidth = g2.getFontMetrics().stringWidth(pauseString);
            int stringHeight = g2.getFontMetrics().getHeight();
            g2.drawString(pauseString, leftBorder+gameWidth/2 - stringWidth/2,
                    topBorder+gameHeight/2 + stringHeight/4);
        }

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
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (pl0.getScore() < maxScore && pl1.getScore() < maxScore) {
            if (!pause) {
                p0.move();
                p1.move();
                ball.move();

                bot.move();
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        String winner = (pl0.getScore() > pl1.getScore())? pl0.getName(): pl1.getName();
        Pong.exitGame(pl0.getName(), pl1.getName(), winner);
    }

    public static class GameKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            pm.keyPressed(e);
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                pause = !pause;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            pm.keyReleased(e);
        }
    }
}
