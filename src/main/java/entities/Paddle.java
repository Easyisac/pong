package entities;

import panels.GamePanel;

public class Paddle {

    public static final int PADDLE_HEIGHT = 75;
    public static final int PADDLE_WIDTH = 10;
    public static final int OFFSET = 50;  // distance between paddle and border

    private final int xPosition;
    private int yPosition;
    private double speed;

    private final Player player;

    public Paddle(Player player) {
        speed = 0;
        this.player = player;
        xPosition = (this.player.getId() == 0) ? GamePanel.GAME_COURT_LEFT_LIMIT + OFFSET : GamePanel.GAME_COURT_RIGHT_LIMIT - OFFSET - PADDLE_WIDTH;
        yPosition = (GamePanel.GAME_COURT_TOP_LIMIT + GamePanel.GAME_COURT_BOTTOM_LIMIT) / 2 - PADDLE_HEIGHT / 2;
    }

    public void move() {
        yPosition += speed;
        yPosition = Math.max(yPosition, GamePanel.GAME_COURT_TOP_LIMIT);
        yPosition = Math.min(yPosition, GamePanel.GAME_COURT_BOTTOM_LIMIT - PADDLE_HEIGHT);
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public double getSpeed() {
        return speed;
    }

    public Player getPlayer() {
        return player;
    }

    public int getBottomEdgePosition() {
        return yPosition + PADDLE_HEIGHT;
    }

    public int getRightEdgePosition() {
        return xPosition + PADDLE_WIDTH;
    }
}
