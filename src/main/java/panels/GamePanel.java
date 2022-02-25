package panels;

import drawers.BallDrawer;
import drawers.Drawer;
import drawers.PaddleDrawer;
import drawers.PlayerDrawer;
import entities.*;
import pong.GameProperties;
import pong.Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    private static PaddleMover paddleMover;
    private static boolean pauseFlag = false;
    private static boolean singlePlayerModeFlag;
    private final Drawer[] drawers = new Drawer[5];

    public int gameHeight = GameProperties.GAME_COURT_HEIGHT;
    public int gameWidth = GameProperties.GAME_COURT_WIDTH;
    public int topFrame = GameProperties.TOP_FRAME;
    public int bottomFrame = GameProperties.BOTTOM_FRAME;
    public int leftFrame = GameProperties.LEFT_FRAME;
    public int rightFrame = GameProperties.RIGHT_FRAME;

    private Player playerLeft;
    private Player playerRight;
    private Paddle paddleLeft;
    private Paddle paddleRight;
    private Ball ball;
    private int velocityModule = 5;
    private int maxScore = 10;
    private Bot bot;

    public GamePanel() {
        Dimension panelSize = new Dimension(gameWidth + leftFrame + rightFrame,
                gameHeight + topFrame + bottomFrame);
        setPreferredSize(panelSize);
        setBackground(Color.black);
        addKeyListener(new GameKeyListener());
        setFocusable(true);
    }

    public void startGame(String playerLeftName, String playerRightName, boolean singlePlayerModeFlag) {

        playerLeft = new Player(playerLeftName, 0);
        playerRight = new Player(playerRightName, 1);
        paddleLeft = new Paddle(playerLeft);
        paddleRight = new Paddle(playerRight);
        ball = new Ball(paddleLeft, paddleRight, playerLeft, playerRight, velocityModule);
        paddleMover = new PaddleMover(paddleLeft, paddleRight);

        drawers[0] = new PaddleDrawer(paddleLeft);
        drawers[1] = new PaddleDrawer(paddleRight);
        drawers[2] = new BallDrawer(ball);
        drawers[3] = new PlayerDrawer(playerLeft);
        drawers[4] = new PlayerDrawer(playerRight);

        GamePanel.singlePlayerModeFlag = singlePlayerModeFlag;
        if (singlePlayerModeFlag) {
            bot = new Bot(paddleRight, ball, this, paddleMover);
            paddleMover.setPaddleRightSpeed(velocityModule*0.75);
        }

        Thread game = new Thread(this);
        game.start();
    }

    public int getVelocityModule() {
        return velocityModule;
    }

    public void setVelocityModule(int velocityModule) {
        this.velocityModule = velocityModule;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    //Standard method, draws static images, calls draw function
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(leftFrame, topFrame, gameWidth, gameHeight);
        if (pauseFlag) {
            String pauseString = "PAUSE";
            g2.setFont(new Font("Arial", Font.PLAIN, 40));
            int stringWidth = g2.getFontMetrics().stringWidth(pauseString);
            int stringHeight = g2.getFontMetrics().getHeight();
            g2.drawString(pauseString, leftFrame + gameWidth / 2 - stringWidth / 2,
                    topFrame + gameHeight / 2 + stringHeight / 4);
        }
        int pixelLine = 5;
        int offset = 20;
        int halfCourt = GameProperties.GAME_COURT_WIDTH/2+ GameProperties.LEFT_FRAME;
        for (int i = 0; i < GameProperties.GAME_COURT_HEIGHT/(pixelLine+offset); i++){
            g2.drawLine(halfCourt, GameProperties.TOP_FRAME + i*(pixelLine+offset), halfCourt, GameProperties.TOP_FRAME + i*(pixelLine+offset) + pixelLine);
        }

        for (Drawer drawer : drawers) {
            drawer.draw(g2);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (playerLeft.getScore() < maxScore && playerRight.getScore() < maxScore) {
            if (!pauseFlag) {
                paddleLeft.move();
                paddleRight.move();
                ball.move();
                if (singlePlayerModeFlag) bot.makeMove();
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String winner = (playerLeft.getScore() > playerRight.getScore()) ? playerLeft.getName() : playerRight.getName();
        Pong.exitGame(playerLeft.getName(), playerRight.getName(), winner, singlePlayerModeFlag);
    }

    public static class GameKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (!(singlePlayerModeFlag && (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN))) {
                paddleMover.keyPressed(e);
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                pauseFlag = !pauseFlag;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            paddleMover.keyReleased(e);
        }
    }
}
