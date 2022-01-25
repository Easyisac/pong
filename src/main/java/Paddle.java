public class Paddle {

    private final int id;
    private final int x;
    private int y;
    private final int topLimY;
    private final int botLimY;

    private final int PADDLE_HEIGHT = 50;
    private final int PADDLE_WIDTH = 10;

    private int velocity;

    public Paddle(int id, int x, int y, int topLimY, int botLimY) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.topLimY = topLimY;
        this.botLimY = botLimY;
        velocity = 0;

    }

    public void move() {
        y += velocity;
        y = Math.max(y, topLimY);
        y = Math.min(y, botLimY-PADDLE_HEIGHT);
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPaddleHeight() { return PADDLE_HEIGHT; }

    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
}
