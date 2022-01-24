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

    public void move() {
        y += velocity;
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
