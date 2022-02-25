package entities;

import pong.GameProperties;

public class Paddle {

    private final int PADDLE_HEIGHT = 75;
    private final int PADDLE_WIDTH = 10;
    private final int OFFSET = 50;

    private final int xPosition;
    private int yPosition;
    private double velocity;

    private final int topLim = GameProperties.GAME_COURT_TOP_LIMIT;
    private final int botLim = GameProperties.GAME_COURT_BOTTOM_LIMIT;
    private final int leftLim = GameProperties.GAME_COURT_LEFT_LIMIT;
    private final int rightLim = GameProperties.GAME_COURT_RIGHT_LIMIT;

    private final Player player;

    public Paddle(Player player) {
        velocity = 0;
        this.player = player;
        xPosition = (this.player.getId() == 0) ? leftLim + OFFSET : rightLim - OFFSET - PADDLE_WIDTH;
        yPosition = (topLim + botLim) / 2 - PADDLE_HEIGHT / 2;
    }

    public void move() {
        yPosition += velocity;
        yPosition = Math.max(yPosition, topLim);
        yPosition = Math.min(yPosition, botLim - PADDLE_HEIGHT);
    }

    public void setyPosition(int yPosition) { this.yPosition = yPosition; }

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

    public int getBottomEdgePosition() { return yPosition + PADDLE_HEIGHT; }

    public int getRightEdgePosition() { return xPosition + PADDLE_WIDTH; }
}
