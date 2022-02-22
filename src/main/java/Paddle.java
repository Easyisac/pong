public class Paddle {

    private final int x;
    private int y;
    private final int topLim = GameProperties.TOP_LIM;
    private final int botLim = GameProperties.BOT_LIM;
    private final int leftLim = GameProperties.LEFT_LIM;
    private final int rightLim = GameProperties.RIGHT_LIM;
    private final Player player;

    private final int PADDLE_HEIGHT = 50;
    private final int PADDLE_WIDTH = 10;
    private final int OFFSET = 50;

    private double velocity;

    public Paddle(Player p) {
        velocity = 0;
        player = p;
        x = (player.getId() == 0) ? leftLim + OFFSET : rightLim - OFFSET - PADDLE_WIDTH;
        y = (topLim + botLim) / 2 - PADDLE_HEIGHT / 2;
    }

    public void move() {
        y += velocity;
        y = Math.max(y, topLim);
        y = Math.min(y, botLim - PADDLE_HEIGHT);
    }

    public Player getPlayer() {
        return player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getVelocity() { return velocity; }

    public int getPADDLE_HEIGHT() { return PADDLE_HEIGHT; }

    public int getPADDLE_WIDTH() { return PADDLE_WIDTH; }

    public void setVelocity(double velocity){
        this.velocity = velocity;
    }

    public void setY(int y) { this.y = y; }
}
