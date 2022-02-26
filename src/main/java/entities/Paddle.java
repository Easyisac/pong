package entities;


import panels.GamePanel;

public class Paddle {

    private static final int PADDLE_HEIGHT = 75;
    private static final int PADDLE_WIDTH = 10;
    private static final int OFFSET = 50;

    private final int xPosition;
    private int yPosition;
    private double velocity;

    private final Player player;

    public Paddle(Player player) {
        velocity = 0;
        this.player = player;
        xPosition = (this.player.getId() == 0) ? GamePanel.GAME_COURT_LEFT_LIMIT + OFFSET : GamePanel.GAME_COURT_RIGHT_LIMIT - OFFSET - PADDLE_WIDTH;
        yPosition = (GamePanel.GAME_COURT_TOP_LIMIT + GamePanel.GAME_COURT_BOTTOM_LIMIT) / 2 - PADDLE_HEIGHT / 2;
    }

    public void move() {
        yPosition += velocity;
        yPosition = Math.max(yPosition, GamePanel.GAME_COURT_TOP_LIMIT);
        yPosition = Math.min(yPosition, GamePanel.GAME_COURT_BOTTOM_LIMIT - PADDLE_HEIGHT);
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public int getPADDLE_HEIGHT() {
        return PADDLE_HEIGHT;
    }

    public int getPADDLE_WIDTH() {
        return PADDLE_WIDTH;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public double getVelocity() {
        return velocity;
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
