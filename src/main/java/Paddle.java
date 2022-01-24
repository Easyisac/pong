public class Paddle {

    private final int id;
    private int x;
    private int y;

    private int velocity;

    public Paddle(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        velocity = 0;
    }

    public void move(int topLimY, int botLimY) {
        // top limits is 0 always
        int nextY = y+velocity;
        if (nextY < topLimY) {
            y = topLimY;
        } else if (nextY > botLimY) { // math.min ??
            y = botLimY;
        } else {
            y = nextY;
        }
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

    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
}
