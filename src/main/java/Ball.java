public class Ball {
    
    private double x;
    private double y;
    private final int xStart;
    private final int yStart;
    private final int topLim;
    private final int botLim;
    private final int leftLim;
    private final int rightLim;

    private double velModule;
    private double velAngle;

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
        velAngle = Math.PI * 2/3.0  ; // Math.random() * 2 * Math.PI;
        velModule = 2;
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
            if (velAngle == 0) {
                velAngle = Math.PI;
            }
            else if (velAngle == Math.PI) {
                velAngle = 0;
            }
            else {
                velAngle = 2 * Math.PI - velAngle;
            }
        }
        else if (checkCollisionsPaddleLeft()){
            bounceOffPaddleLeft();
        }
        else if (checkCollisionsPaddleRight()){
            bounceOffPaddleRight();
        }
        y += Math.sin(velAngle) * velModule;
        x +=  Math.cos(velAngle) * velModule;
    }

    public boolean checkCollisionsPaddleLeft(){
        return (checkCollisionsPaddleLeftHorizontalSide() || checkCollisionsPaddleLeftVerticalSide());
    }

    public boolean checkCollisionsPaddleRight(){
        return (checkCollisionsPaddleRightHorizontalSide() || checkCollisionsPaddleRightVerticalSide());
    }

    private void bounceOffPaddleRight() {
        /*int yImpact = y + yVelocity;
        int yMid = pRight.getY() + pRight.getPADDLE_HEIGHT()/2;
        int yRel = yImpact - yMid;
        yVelocity = (yRel-pRight.getPADDLE_HEIGHT()/2)/(pRight.getPADDLE_HEIGHT()/2) * Math.abs(xVelocity) + Math.abs(xVelocity);

        xVelocity = -xVelocity;*/
    }

    private void bounceOffPaddleLeft() {
        //xVelocity = -xVelocity;
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVelModule() {
        return velModule;
    }

    public double getVelAngle() {
        return velAngle;
    }

    public void setVelModule(double velModule) {
        this.velModule = velModule;
    }

    public void setVelAngle(double velAngle) {
        this.velAngle = velAngle % (2 * Math.PI);
    }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public int getxStart() { return xStart; }

    public int getyStart() { return yStart; }

    public int getBALL_RADIUS() { return BALL_RADIUS; }

    public void reset() {
        x = xStart;
        y = yStart;
        velAngle = Math.random() * 2 * Math.PI;;
    }

    public boolean checkCollisionsTop() {
        return (y - BALL_RADIUS + Math.sin(velAngle) * velModule) <= topLim;
    }

    public boolean checkCollisionsBottom() {
        return (y + BALL_RADIUS + Math.cos(velAngle) * velModule) >= botLim;
    }

    public boolean checkCollisionsLeft() {
        return (x - BALL_RADIUS + Math.cos(velAngle) * velModule) <= leftLim;
    }

    public boolean checkCollisionsRight() {
        return (x + BALL_RADIUS + Math.cos(velAngle) * velModule) >= rightLim;
    }

    public boolean checkCollisionsPaddleLeftVerticalSide(){
        boolean condX = pLeft.getX() + pLeft.getPADDLE_WIDTH() <= (x + Math.cos(velAngle) * velModule) &&
                (x + Math.cos(velAngle) * velModule) <= pLeft.getX() + pLeft.getPADDLE_WIDTH() + bOff;
        boolean condY = pLeft.getY() <= (y + Math.sin(velAngle) * velModule) && (y + Math.sin(velAngle) * velModule) <= pLeft.getY() + pLeft.getPADDLE_HEIGHT();
        return (condX && condY);
    }

    public boolean checkCollisionsPaddleLeftHorizontalSide(){
        boolean condX = pLeft.getX() <= (x + Math.cos(velAngle) * velModule) && (x + Math.cos(velAngle) * velModule) <= pLeft.getX() + pLeft.getPADDLE_WIDTH() + bOff;
        boolean condYTop = pLeft.getY() - bOff <= (y + Math.sin(velAngle) * velModule) && (y + Math.sin(velAngle) * velModule) <= pLeft.getY();
        boolean condYBot = pLeft.getY() + pLeft.getPADDLE_HEIGHT() <= (y + Math.sin(velAngle) * velModule) &&
                (y + Math.sin(velAngle) * velModule) <= pLeft.getY() + pLeft.getPADDLE_HEIGHT()+ bOff;
        return (condX && (condYTop || condYBot));
    }

    public boolean checkCollisionsPaddleRightVerticalSide(){
        boolean condX = pRight.getX() - bOff <= (x + Math.cos(velAngle) * velModule) && (x + Math.cos(velAngle) * velModule) <= pRight.getX();
        boolean condY = pRight.getY() <= (y + Math.sin(velAngle) * velModule) && (y + Math.sin(velAngle) * velModule) <= pRight.getY() + pRight.getPADDLE_HEIGHT();
        return (condX && condY);
    }

    public boolean checkCollisionsPaddleRightHorizontalSide(){
        boolean condX = pRight.getX() - bOff <= (x + Math.cos(velAngle) * velModule) && (x + Math.cos(velAngle) * velModule) <= pRight.getX() + pRight.getPADDLE_WIDTH();
        boolean condYTop = pRight.getY() - bOff <= (y + Math.sin(velAngle) * velModule) && (y + Math.sin(velAngle) * velModule) <= pRight.getY();
        boolean condYBot = pRight.getY() + pRight.getPADDLE_HEIGHT() <= (y + Math.sin(velAngle) * velModule) &&
                (y + Math.sin(velAngle) * velModule) <= pRight.getY() + pRight.getPADDLE_HEIGHT()+ bOff;
        return (condX && (condYTop || condYBot));
    }



}
