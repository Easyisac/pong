public class Ball {
    
    private int x;
    private int y;
    private final int topLim;
    private final int botLim;
    private final int leftLim;
    private final int rightLim;
    private int xVelocity;
    private int yVelocity;


    public Ball(int topLim, int botLim, int leftLim, int rightLim) {
        this.x = rightLim/2;
        this.y = botLim/2;
        this.topLim = topLim;
        this.botLim = botLim;
        this.leftLim = leftLim;
        this.rightLim = rightLim;
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

}
