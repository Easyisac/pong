public class Paddle {

    private final int id;
    private final int x;
    private int y;
    private final int topLim;
    private final int botLim;
    private final int leftLim;
    private final int rightLim;

    private final int PADDLE_HEIGHT = 50;
    private final int PADDLE_WIDTH = 10;
    private final int OFFSET = 50;

    private int velocity;

    public Paddle(int id, int topLim, int botLim, int leftLim, int rightLim) {
        this.id = id;
        this.topLim = topLim;
        this.botLim = botLim;
        this.leftLim = leftLim;
        this.rightLim = rightLim;
        velocity = 0;
        x = (id == 0) ? leftLim + OFFSET : rightLim - OFFSET - PADDLE_WIDTH;
        y = (topLim + botLim) / 2 - PADDLE_HEIGHT / 2;
    }

    public void move() {
        y += velocity;
        y = Math.max(y, topLim);
        y = Math.min(y, botLim - PADDLE_HEIGHT);
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

    public int getVelocity() { return velocity; }

    public int getPADDLE_HEIGHT() { return PADDLE_HEIGHT; }

    public int getPADDLE_WIDTH() { return PADDLE_WIDTH; }

    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
}
