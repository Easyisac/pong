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
        xStart = (rightLim + leftLim) / 2;
        yStart = (topLim + botLim) / 2;
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
        if (checkCollisionsTop()){
            bounceOffTopBoundary();
        }
        else if (checkCollisionsBottom()){
            bounceOffBottomBoundary();
        }
        else if (checkCollisionsLeft()){
            goalScoredLeft();
        }
        else if (checkCollisionsRight()){
            goalScoredRight();
        }
        else if (checkCollisionsPaddleLeft()){
            bounceOffPaddleLeft();
        }
        else if (checkCollisionsPaddleRight()){
            bounceOffPaddleRight();
        }
        else {
            y += yVelocity;
            x += xVelocity;
        }
    }

    private void bounceOffPaddleRight() {
        x -= xVelocity;
        y += yVelocity;
    }

    private void bounceOffPaddleLeft() {
        x -= xVelocity;
        y += yVelocity;
    }

    private void goalScoredRight() {
    }

    private void goalScoredLeft() {
    }

    private void bounceOffBottomBoundary() {
        x += xVelocity;
        y -= yVelocity;
    }

    private void bounceOffTopBoundary() {
       // int pixelsFromTopBoundary = Math.abs(y - BALL_RADIUS - topLim);
       // yVelocity = -yVelocity - 2*pixelsFromTopBoundary;
        x += xVelocity;
        y -= yVelocity;
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
        return (y - BALL_RADIUS + yVelocity) <= topLim;
    }

    public boolean checkCollisionsBottom() {
        return (y + BALL_RADIUS + yVelocity) >= botLim;
    }

    public boolean checkCollisionsLeft() {
        return (x - BALL_RADIUS + xVelocity) <= leftLim;
    }

    public boolean checkCollisionsRight() {
        return (x + BALL_RADIUS + xVelocity) >= rightLim;
    }

    public boolean checkCollisionsPaddleLeft(){
        boolean condX = pLeft.getX() <= (x + xVelocity) && (x + xVelocity) <= pLeft.getX() + pLeft.getPADDLE_WIDTH();
        boolean condY = pLeft.getY() <= (y + yVelocity) && (y + yVelocity) <= pLeft.getY() + pLeft.getPADDLE_HEIGHT();
        return (condX && condY);
    }

    public boolean checkCollisionsPaddleRight(){
        boolean condX = pRight.getX() <= (x + xVelocity) && (x + xVelocity) <= pRight.getX() + pRight.getPADDLE_WIDTH();
        boolean condY = pRight.getY() <= (y + yVelocity) && (y + yVelocity) <= pRight.getY() + pRight.getPADDLE_HEIGHT();
        return (condX && condY);
    }


}
