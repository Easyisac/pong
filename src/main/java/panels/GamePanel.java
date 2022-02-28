package panels;

import drawers.BallDrawer;
import drawers.Drawer;
import drawers.PaddleDrawer;
import drawers.PlayerDrawer;
import entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    public static final int GAME_COURT_HEIGHT = 500;
    public static final int GAME_COURT_WIDTH = 800;
    public static final int TOP_FRAME = 100;
    public static final int BOTTOM_FRAME = 50;
    public static final int SIDE_FRAME = 50;
    public static final int GAME_COURT_TOP_LIMIT = TOP_FRAME;
    public static final int GAME_COURT_BOTTOM_LIMIT = GAME_COURT_HEIGHT + TOP_FRAME;
    public static final int GAME_COURT_LEFT_LIMIT = SIDE_FRAME;
    public static final int GAME_COURT_RIGHT_LIMIT = GAME_COURT_WIDTH + SIDE_FRAME;

    private static PaddleMover paddleMover;
    private static boolean pauseFlag = false;
    private static boolean singlePlayerModeFlag;
    private final Drawer[] drawers = new Drawer[5];

    private Player playerLeft;
    private Player playerRight;
    private Paddle paddleLeft;
    private Paddle paddleRight;
    private Ball ball;
    private int ballVelocityModule = 5;
    private int maxScore = 10;
    private Bot bot;

    public GamePanel() {
        Dimension panelSize = new Dimension(GAME_COURT_WIDTH + SIDE_FRAME + SIDE_FRAME,
                GAME_COURT_HEIGHT + TOP_FRAME + BOTTOM_FRAME);
        setPreferredSize(panelSize);
        setBackground(Color.black);
        addKeyListener(new GameKeyListener());
        setFocusable(true);
    }

    public int getBallVelocityModule() {
        return ballVelocityModule;
    }

    public void setBallVelocityModule(int ballVelocityModule) {
        this.ballVelocityModule = ballVelocityModule;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public void startGame(String playerLeftName, String playerRightName, boolean singlePlayerModeFlag) {
        playerLeft = new Player(playerLeftName, 0);
        playerRight = new Player(playerRightName, 1);
        paddleLeft = new Paddle(playerLeft);
        paddleRight = new Paddle(playerRight);
        ball = new Ball(paddleLeft, paddleRight, playerLeft, playerRight, ballVelocityModule);
        paddleMover = new PaddleMover(paddleLeft, paddleRight);

        drawers[0] = new PaddleDrawer(paddleLeft);
        drawers[1] = new PaddleDrawer(paddleRight);
        drawers[2] = new BallDrawer(ball);
        drawers[3] = new PlayerDrawer(playerLeft);
        drawers[4] = new PlayerDrawer(playerRight);

        GamePanel.singlePlayerModeFlag = singlePlayerModeFlag;
        if (singlePlayerModeFlag) {
            bot = new Bot(paddleRight, ball, this, paddleMover);
            paddleMover.setPaddleRightSpeed(ballVelocityModule * 0.6);
        }
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(SIDE_FRAME, TOP_FRAME, GAME_COURT_WIDTH, GAME_COURT_HEIGHT);
        if (pauseFlag) {
            String pauseString = "PAUSE";
            g2.setFont(new Font("Arial", Font.PLAIN, 40));
            int stringWidth = g2.getFontMetrics().stringWidth(pauseString);
            int stringHeight = g2.getFontMetrics().getHeight();
            g2.drawString(pauseString, SIDE_FRAME + GAME_COURT_WIDTH / 2 - stringWidth / 2,
                    TOP_FRAME + GAME_COURT_HEIGHT / 2 + stringHeight / 4);
        }
        int pixelLine = 5;
        int offset = 20;
        int halfCourt = GAME_COURT_WIDTH / 2 + SIDE_FRAME;
        for (int i = 0; i < GAME_COURT_HEIGHT / (pixelLine + offset); i++) {
            g2.drawLine(halfCourt, TOP_FRAME + i * (pixelLine + offset), halfCourt, TOP_FRAME + i * (pixelLine + offset) + pixelLine);
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
        GameManager.endMenu(winner);
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
