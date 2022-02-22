public class Paddle {

    private final int PADDLE_HEIGHT = 50;
    private final int PADDLE_WIDTH = 10;
    private final int OFFSET = 50;

    private final int xPosition;
    private int yPosition;
    private double velocity;

    private final int topLim = GameProperties.TOP_LIM;
    private final int botLim = GameProperties.BOT_LIM;
    private final int leftLim = GameProperties.LEFT_LIM;
    private final int rightLim = GameProperties.RIGHT_LIM;

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
}
