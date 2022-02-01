public class Ball {
    
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;
    private final int xStart;
    private final int yStart;
    private final int topLim;
    private final int botLim;
    private final int leftLim;
    private final int rightLim;

    private final Paddle pLeft;
    private final Paddle pRight;

    private final int BALL_RADIUS = 10;


    public Ball(int topLim, int botLim, int leftLim, int rightLim, Paddle pLeft, Paddle pRight) {
        xStart = (rightLim + leftLim) / 2 - BALL_RADIUS;
        yStart = (topLim + botLim) / 2 - BALL_RADIUS;
        x = xStart;
        y = yStart;
        this.topLim = topLim;
        this.botLim = botLim;
        this.leftLim = leftLim;
        this.rightLim = rightLim;
        this.pLeft = pLeft;
        this.pRight = pRight;
        xVelocity = 0;
        yVelocity = 0;
    }

    public void move() {
        y += yVelocity;
        x += xVelocity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setxVelocity(int xVelocity) { this.xVelocity = xVelocity; }

    public void setyVelocity(int yVelocity) { this.yVelocity = yVelocity; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public int getxStart() { return xStart; }

    public int getyStart() { return yStart; }

    public int getBALL_RADIUS() { return BALL_RADIUS; }

    public void reset() {
        x = xStart;
        y = yStart;
    }

    public boolean checkCollisionsTop() {
        return y == (topLim + BALL_RADIUS);
    }

    public boolean checkCollisionsBottom() {
        return y == (botLim - BALL_RADIUS);
    }

    public boolean checkCollisionsLeft() {
        return x == (leftLim + BALL_RADIUS);
    }

    public boolean checkCollisionsRight() {
        return x == (rightLim - BALL_RADIUS);
    }
}
