public class Ball {
    
    private int x;
    private int y;
    private final int xStart;
    private final int yStart;
    private final int topLim;
    private final int botLim;
    private final int leftLim;
    private final int rightLim;
    private int xVelocity;
    private int yVelocity;


    public Ball(int topLim, int botLim, int leftLim, int rightLim) {
        xStart = rightLim/2;
        yStart = botLim/2;
        x = xStart;
        y = yStart;
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

    public int getxStart() { return xStart; }

    public int getyStart() { return yStart; }

    public void reset() {
        x = xStart;
        y = yStart;
    }

}
