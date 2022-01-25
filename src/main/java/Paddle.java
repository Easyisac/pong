public class Paddle {

    private final int id;
    private final int x;
    private int y;
    private final int topLim;
    private final int botLim;

    private final int PADDLE_HEIGHT = 50;
    private final int PADDLE_WIDTH = 10;

    private int velocity;

    public Paddle(int id, int x, int y, int topLim, int botLim) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.topLim = topLim;
        this.botLim = botLim;
        velocity = 0;

    }

    public void move() {
        y += velocity;
        y = Math.max(y, topLim);
        y = Math.min(y, botLim -PADDLE_HEIGHT);
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

    public int getPADDLE_HEIGHT() { return PADDLE_HEIGHT; }

    public int getPADDLE_WIDTH() { return PADDLE_WIDTH; }

    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
}
