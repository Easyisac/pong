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
    private final int bOff = BALL_RADIUS;


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
        xVelocity = 1;
        yVelocity = 0;
    }

    public void move() {
        if (checkCollisionsLeft()){
            goalScoredLeft();
            return ;
        }
        else if (checkCollisionsRight()){
            goalScoredRight();
            return ;
        }
        else if (checkCollisionsTop() || checkCollisionsBottom()){
            yVelocity = -yVelocity;
        }
        else if (checkCollisionsPaddleLeft()){
            bounceOffPaddleLeft();
        }
        else if (checkCollisionsPaddleRight()){
            bounceOffPaddleRight();
        }
        y += yVelocity;
        x += xVelocity;
    }

    public boolean checkCollisionsPaddleLeft(){
        return (checkCollisionsPaddleLeftHorizontalSide() || checkCollisionsPaddleLeftVerticalSide());
    }

    public boolean checkCollisionsPaddleRight(){
        return (checkCollisionsPaddleRightHorizontalSide() || checkCollisionsPaddleRightVerticalSide());
    }

    private void bounceOffPaddleRight() {
        int yImpact = y + yVelocity;
        int yMid = pRight.getY() + pRight.getPADDLE_HEIGHT()/2;
        int yRel = yImpact - yMid;
        yVelocity = (yRel-pRight.getPADDLE_HEIGHT()/2)/pRight.getPADDLE_HEIGHT()/2 * Math.abs(xVelocity) + Math.abs(xVelocity);
        xVelocity = -xVelocity;
    }

    private void bounceOffPaddleLeft() {
        xVelocity = -xVelocity;
    }

    private void goalScoredRight() {
        reset();
    }

    private void goalScoredLeft() {
        reset();
    }

/*    private void bounceOffBottomBoundary() {
        x += xVelocity;
        yVelocity = -yVelocity;
        y += yVelocity;
    }

    private void bounceOffTopBoundary() {
       // int pixelsFromTopBoundary = Math.abs(y - BALL_RADIUS - topLim);
       // yVelocity = -yVelocity - 2*pixelsFromTopBoundary;
        x += xVelocity;
        yVelocity = -yVelocity;
        y += yVelocity;
    }*/

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
        xVelocity = (int)(Math.random()*3)+1;
        yVelocity = (int)(Math.random()*3)+1;
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

    public boolean checkCollisionsPaddleLeftVerticalSide(){
        boolean condX = pLeft.getX() + pLeft.getPADDLE_WIDTH() <= (x + xVelocity) &&
                (x + xVelocity) <= pLeft.getX() + pLeft.getPADDLE_WIDTH() + bOff;
        boolean condY = pLeft.getY() <= (y + yVelocity) && (y + yVelocity) <= pLeft.getY() + pLeft.getPADDLE_HEIGHT();
        return (condX && condY);
    }

    public boolean checkCollisionsPaddleLeftHorizontalSide(){
        boolean condX = pLeft.getX() <= (x + xVelocity) && (x + xVelocity) <= pLeft.getX() + pLeft.getPADDLE_WIDTH() + bOff;
        boolean condYTop = pLeft.getY() - bOff <= (y + yVelocity) && (y + yVelocity) <= pLeft.getY();
        boolean condYBot = pLeft.getY() + pLeft.getPADDLE_HEIGHT() <= (y + yVelocity) &&
                (y + yVelocity) <= pLeft.getY() + pLeft.getPADDLE_HEIGHT()+ bOff;
        return (condX && (condYTop || condYBot));
    }

    public boolean checkCollisionsPaddleRightVerticalSide(){
        boolean condX = pRight.getX() - bOff <= (x + xVelocity) && (x + xVelocity) <= pRight.getX();
        boolean condY = pRight.getY() <= (y + yVelocity) && (y + yVelocity) <= pRight.getY() + pRight.getPADDLE_HEIGHT();
        return (condX && condY);
    }

    public boolean checkCollisionsPaddleRightHorizontalSide(){
        boolean condX = pRight.getX() - bOff <= (x + xVelocity) && (x + xVelocity) <= pRight.getX() + pRight.getPADDLE_WIDTH();
        boolean condYTop = pRight.getY() - bOff <= (y + yVelocity) && (y + yVelocity) <= pRight.getY();
        boolean condYBot = pRight.getY() + pRight.getPADDLE_HEIGHT() <= (y + yVelocity) &&
                (y + yVelocity) <= pRight.getY() + pRight.getPADDLE_HEIGHT()+ bOff;
        return (condX && (condYTop || condYBot));
    }



}
